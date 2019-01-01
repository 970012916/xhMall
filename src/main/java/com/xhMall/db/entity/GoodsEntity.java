package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseEntity;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/17,21:43
 */
public class GoodsEntity extends BaseEntity {

    private static final long serialVersionUID = -7269887520543141319L;

    private String goodsTypeCode;

    private String pathCode;

    private String goodsTypeName;

    private String goodsTypeId;

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getPathCode() {
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }
}
