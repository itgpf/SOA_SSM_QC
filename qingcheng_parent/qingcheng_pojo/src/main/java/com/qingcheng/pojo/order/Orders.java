package com.qingcheng.pojo.order;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-08-27 13:31
 */

public class Orders implements Serializable {
    private Order order;
    private List<OrderItem> orderItemList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}