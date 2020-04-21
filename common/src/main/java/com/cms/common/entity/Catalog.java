package com.cms.common.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Catalog implements Serializable {
    private Byte id;
    private String catalogName;

}