package com.xhMall.db.entity.base;

import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/19,22:29
 */
public class BaseSelectListEntity extends BaseBean {

    private static final long serialVersionUID = 3117419677041238204L;

    /**
     * 下拉框列表类别KeY
     */
    private String selectedKey = "";

    /**
     * 下拉框那个元素
     */
    private List<BaseSelectItemEntity> baseSelectList = null;

    public String getSelectedKey() {
        return selectedKey;
    }

    public void setSelectedKey(String selectedKey) {
        this.selectedKey = selectedKey;
    }

    public List <BaseSelectItemEntity> getBaseSelectList() {
        return baseSelectList;
    }

    public void setBaseSelectList(List <BaseSelectItemEntity> baseSelectList) {
        this.baseSelectList = baseSelectList;
    }
}
