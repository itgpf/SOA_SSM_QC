package com.qingcheng.service.order;

/*
* 购物车服务
* */

import java.util.List;
import java.util.Map;

public interface CartService {
    //从redis中加载购物车类型
    public List<Map<String,Object>> findCartList(String username);
   //添加商品到购物车
    public void addItem(String username,String skuId,Integer num);


    public boolean updateChecked(String username,String skuId,boolean checked);

    //删除选中的购物车
    public void  deleteCheckedCart(String username);
   //计算购物车的优惠金额
    public int  preferential(String username);
    //刷新购物车
    public List<Map<String,Object>> findNewOrderItemList(String username);
}
