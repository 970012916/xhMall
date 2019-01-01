package com.xhMall.common.util;

import com.xhMall.db.entity.base.BaseSelectItemEntity;
import com.xhMall.global.GlobalManager;

import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/26,22:22
 */
public class ComboxListUtil {
    public static List<BaseSelectItemEntity> getComboxListInfo(String comboxType){
        List<BaseSelectItemEntity> baseList = GlobalManager.getXMLManager().geListInfo(comboxType);
        return baseList;
    }
}
