package com.qingcheng.pojo.order;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-08 20:53
 */
@Table(name = "tb_statistics")
public class Statistics implements Serializable {
    @Id
    private String id;

    private Long number;

    private Long place;

    private Long order;

    private Long quantity_number;

    private Long valid;

    private String place_money;

    private String refund_money;

    private Long refund_people;

    private Long refund_order;

    private Long refund_number;

    private String payment_number;

    private String guest_money;
}