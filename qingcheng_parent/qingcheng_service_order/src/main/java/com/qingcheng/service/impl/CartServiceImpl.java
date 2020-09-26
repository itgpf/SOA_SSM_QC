package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.pojo.goods.Category;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.pojo.order.OrderItem;
import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SkuService;
import com.qingcheng.service.order.CartService;
import com.qingcheng.service.order.PreferentialService;
import com.qingcheng.util.CacheKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-21 18:13
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private SkuService skuService;
    @Reference
    private CategoryService categoryService;
    @Override
    public List<Map<String, Object>> findCartList(String username) {
        System.out.println("从redis中提取购物车"+username);
        List<Map<String, Object>> cartList = (List<Map<String, Object>>) redisTemplate.boundHashOps(CacheKey.CART_LIST).get(username);
        if (cartList==null){
            cartList=new ArrayList<>();
        }

        return cartList;
    }

    @Override
    public void addItem(String username, String skuId, Integer num) {
        //实现思路 遍历购物车 如果购物车存在该商品 累加数量 不存在添加购物车项
        //获取购物车
        List<Map<String, Object>> cartList = findCartList(username);

        boolean flag=false;//是否在购物车存在
        for (Map<String, Object> map : cartList) {
           OrderItem orderItem = (OrderItem) map.get("item");
           if (orderItem.getSkuId().equals(skuId)){
               //购物车存在该商品
               if (orderItem.getNum()<=0){
                   cartList.remove(map);
                   flag=true;
                   break;
               }
             int weight=orderItem.getWeight()/orderItem.getNum();

             orderItem.setNum(orderItem.getNum()+num);
             orderItem.setMoney(orderItem.getPrice()*orderItem.getNum());
             orderItem.setWeight(orderItem.getNum()*weight);

               if (orderItem.getNum()<=0){
                   cartList.remove(map);
               }

               flag=true;
               break;
           }
        }
        //如果购物车中没有该商品则添加
        if (flag==false){

            Sku sku = skuService.findById(skuId);
            if (sku==null){
                throw new RuntimeException("商品不存在");
            }
            if (!"1".equals(sku.getStatus())){
                throw new RuntimeException("商品状态不合法");
            }
            if (num<=0){
                //s数量不能为0 或负数
                throw new RuntimeException("商品数量不合法");
            }
          OrderItem orderItem = new OrderItem();


          orderItem.setSkuId(skuId);
          orderItem.setSpuId(sku.getSpuId());
          orderItem.setNum(num);
          orderItem.setImage(sku.getImage());
          orderItem.setPrice(sku.getPrice());
          orderItem.setName(sku.getName());
          orderItem.setMoney(orderItem.getPrice()*num);//金额计算
          if (sku.getWeight()==null){
              sku.setWeight(0);
          }
          orderItem.setWeight(sku.getWeight()*num);


            //商品分类
            orderItem.setCategoryId3(sku.getCategoryId());

            Category category3= (Category) redisTemplate.boundHashOps(CacheKey.CATEGORY).get(sku.getCategoryId());
            if (category3==null){
                category3 = categoryService.findById(sku.getCategoryId());
                redisTemplate.boundHashOps(CacheKey.CATEGORY).put(sku.getCategoryId(),category3);
            }
            orderItem.setCategoryId2(category3.getParentId());

            Category category2= (Category) redisTemplate.boundHashOps(CacheKey.CATEGORY).get(category3.getParentId());
            if (category2==null){
                category2 = categoryService.findById(category3.getParentId());
                redisTemplate.boundHashOps(CacheKey.CATEGORY).put(category3.getParentId(),category2);
            }
            orderItem.setCategoryId1(category2.getParentId());

           Map map = new HashMap();
           map.put("item",orderItem);
           map.put("checked",true);//默认选中

            cartList.add(map);

        }
        redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
    }

    @Override
    public boolean updateChecked(String username, String skuId, boolean checked) {
        List<Map<String, Object>> cartList = findCartList(username);
        boolean isOk=false;
        for (Map<String, Object> map : cartList) {
            OrderItem orderItem = (OrderItem) map.get("item");
            if (orderItem.getSkuId().equals(skuId)){
                map.put("checked",checked);
                isOk=true;
                break;
            }
        }
        if (isOk){
            redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
        }
        return isOk;
    }

    @Override
    public void deleteCheckedCart(String username) {
       //获得未选中的购物车
        List<Map<String, Object>> cartList = findCartList(username).stream().filter(cart -> (boolean) cart.get("checked") == false).collect(Collectors.toList());
        redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);
    }

    @Autowired
    private PreferentialService preferentialService;

    @Override
    public int preferential(String username) {
        //获取选中的购物车 List<OrderItem>
        List<OrderItem> orderItemList = findCartList(username).stream().filter(cart -> (boolean) cart.get("checked") == true).map(cart -> (OrderItem) cart.get("item")).collect(Collectors.toList());
        //按照分类聚合统计 每个分类金额
        // 分类    金额
        // 1       123
        // 2       1455
        Map<Integer, IntSummaryStatistics> cartMap = orderItemList.stream().collect(Collectors.groupingBy(OrderItem::getCategoryId3, Collectors.summarizingInt(OrderItem::getMoney)));

        //循环结果统计每个分类的优惠金额并累加
        int allPreMoney=0;//累计优惠金额
        for (Integer categoryId : cartMap.keySet()) {
            //获取品类消费金额
            int money = (int) cartMap.get(categoryId).getSum();
            int premoney = preferentialService.findPreMoneyByCategoryId(categoryId, money);
            System.out.println("分类："+categoryId+"消费金额："+money+"优惠金额:"+premoney);
            allPreMoney+=premoney;
        }
        return allPreMoney;
    }

    @Override
    public List<Map<String, Object>> findNewOrderItemList(String username) {
      //获取购物车
        List<Map<String, Object>> cartList = findCartList(username);

        //循环购物车 刷新价格
        for (Map<String, Object> cart : cartList) {
            OrderItem orderItem = (OrderItem) cart.get("item");
            Sku sku = skuService.findById(orderItem.getSkuId());
            orderItem.setPrice(sku.getPrice());
            orderItem.setMoney(sku.getPrice()*orderItem.getNum());

        }


        //保存购物车
        redisTemplate.boundHashOps(CacheKey.CART_LIST).put(username,cartList);




        return cartList;
    }
}