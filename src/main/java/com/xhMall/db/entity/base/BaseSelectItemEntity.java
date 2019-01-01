package com.xhMall.db.entity.base;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/19,22:31
 */
public class BaseSelectItemEntity extends BaseBean {

    private static final long serialVersionUID = -2979424740153466865L;

    private String key = "";

    private String value = "";

    public BaseSelectItemEntity(){}

    public BaseSelectItemEntity(String key,String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
