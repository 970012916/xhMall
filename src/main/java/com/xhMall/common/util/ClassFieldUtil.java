package com.xhMall.common.util;


import com.xhMall.exception.MallException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassFieldUtil {

    public static boolean isNullOrBlank(Object obj) {

        Class clazz = obj.getClass();

        Field[] fields = clazz.getDeclaredFields();

        String name = null;
        for(Field field : fields ) {
            name = field.getName();
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name,clazz);
                //获取get方法
                Method method = propertyDescriptor.getReadMethod();
                //调用get方法
                Object object = method.invoke(obj);
                if(null == object) {
                    return true;
                }else {
                    if("java.lang.String".equals(object.getClass())) {
                        String objectStr = (String) object;
                        if(CommonStringUtil.isEmpty(objectStr)) {
                            return true;
                        }
                    }
                }

            } catch (Exception e) {
                MallException mallException = new MallException();
                mallException.setCode(500);
                mallException.setMessage("类属性有空值");
                throw mallException;

            }

        }
        return false;
    }




}
