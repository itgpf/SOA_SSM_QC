package com.qingcheng.pojo.goods;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-08-27 10:32
 */
@Table(name = "tb_marketable")
public class Marketable implements Serializable {
    @Id
    private String id;

    private String history_marketable; //上一次的上架状态

    private String update_marketable; //本次的上架状态

    private Date history_time;

    private Date update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHistory_marketable() {
        return history_marketable;
    }

    public void setHistory_marketable(String history_marketable) {
        this.history_marketable = history_marketable;
    }

    public String getUpdate_marketable() {
        return update_marketable;
    }

    public void setUpdate_marketable(String update_marketable) {
        this.update_marketable = update_marketable;
    }

    public Date getHistory_time() {
        return history_time;
    }

    public void setHistory_time(Date history_time) {
        this.history_time = history_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}