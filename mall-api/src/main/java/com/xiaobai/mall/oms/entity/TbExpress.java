package com.xiaobai.mall.oms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 快递公司表
 * @create 2020-03-28 10:45
 **/
public class TbExpress implements Serializable {

    private Long id;
    private String expressName;
    private Integer sortOrder;
    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
