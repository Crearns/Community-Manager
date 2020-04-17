package com.cms.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
public class Community implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModifed;
    private String name;
    private Byte catalogId;
    private String decription;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModifed() {
        return gmtModifed;
    }

    public void setGmtModifed(Date gmtModifed) {
        this.gmtModifed = gmtModifed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Byte catalogId) {
        this.catalogId = catalogId;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}