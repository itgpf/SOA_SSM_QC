package com.qingcheng.pojo.system;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-09 08:52
 */
@Table(name = "tb_admin_role")
public class AdminRole implements Serializable {
    @Id
    private Integer adminId;

    private Integer roleId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}