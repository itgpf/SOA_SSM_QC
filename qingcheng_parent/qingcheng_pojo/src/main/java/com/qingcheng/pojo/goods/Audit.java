package com.qingcheng.pojo.goods;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-08-27 10:10
 */
@Table(name = "tb_audit")
public class Audit implements Serializable {
    @Id
    private String id;

    private String history_status; //上一次的审核状态

    private String update_status; //当前审核状态

    private String history_message; //上一次的日志信息

    private String update_message; //当前日志信息

    private Date history_time; //上一次的修改时间

    private Date update_time; //当前修改时间

    private String update_marketable; //上一次的上架状态

    private String history_marketable; //更新后的上架状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHistory_status() {
        return history_status;
    }

    public void setHistory_status(String history_status) {
        this.history_status = history_status;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }

    public String getHistory_message() {
        return history_message;
    }

    public void setHistory_message(String history_message) {
        this.history_message = history_message;
    }

    public String getUpdate_message() {
        return update_message;
    }

    public void setUpdate_message(String update_message) {
        this.update_message = update_message;
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

    public String getUpdate_marketable() {
        return update_marketable;
    }

    public void setUpdate_marketable(String update_marketable) {
        this.update_marketable = update_marketable;
    }

    public String getHistory_marketable() {
        return history_marketable;
    }

    public void setHistory_marketable(String history_marketable) {
        this.history_marketable = history_marketable;
    }
}