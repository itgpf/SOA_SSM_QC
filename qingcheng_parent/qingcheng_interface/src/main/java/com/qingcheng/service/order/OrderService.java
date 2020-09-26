package com.qingcheng.service.order;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.order.Order;
import com.qingcheng.pojo.order.Orders;

import java.util.*;

/**
 * order业务逻辑层
 */
public interface OrderService {


    public List<Order> findAll();


    public PageResult<Order> findPage(int page, int size);


    public List<Order> findList(Map<String,Object> searchMap);


    public PageResult<Order> findPage(Map<String,Object> searchMap,int page, int size);


    public Order findById(String id);

    Order findByIds(String[] ids);


    public Map<String,Object> add(Order order);


    public void update(Order order);

    public Orders findByOrdersId(String id);

    public void delete(String id);
    /*
    * 批量发货
    * */
    public void batchSend(List<Order> orders);
  /*
  * 订单超时
  * */
    public void orderTimeOutLogic();
    /*
    * 订单合并
    * */
    public Order merge(Map<String,String> mergeMap);
   /*
   * 订单拆分
   * */
   public void split(List<HashMap<String,Integer>> splitList);
   /*
   * 支付完成 修改订单状态
   * */
    public void updatePayStatus(String orderId,String transactionId);


}
