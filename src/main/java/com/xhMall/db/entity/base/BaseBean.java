package com.xhMall.db.entity.base;

import com.xhMall.common.constant.Constant;
import com.xhMall.common.util.CommonStringUtil;
import com.xhMall.common.util.LogUtil;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/12,22:03
 */
public class BaseBean implements Serializable{

    private static final long serialVersionUID = 2904563879673279448L;

    /**
     * 消除前后半角空格
     */
    public void removeAllSpaces(){
        removeSpaces(this);
    }


    /**
     * 消除对象属性的前后半角空格
     * @param obj
     * @throws NoSuchMethodException
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    private void removeSpaces(Object obj){
        if (null == obj) {
            return;
        }
        //获取全部方法
        //getMethods()方法获取的是所有的public的函数，包括父类继承而来的
        //getDeclaredMethods()获取的是所有该类自己声明的方法
        @SuppressWarnings({"rawtypes","unchecked"})
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method m : methods ){
            String methodName = m.getName();
            //set方法
            if (methodName.startsWith("set")){
                try {
                    Method mGet = null;
                    //获取方法对应的属性名
                    String propName = methodName.substring(Constant.number_3);
                    try {
                        mGet = obj.getClass().getMethod("get" + propName);
                    } catch (Exception e) {
                        LogUtil.doSkip();
                    }
                    if (null == mGet) {
                        continue;
                    }

                    //get方法返回类型
                    Class<?> getType = mGet.getReturnType();
                    //set方法参数类型
                    Class<?>[] type = m.getParameterTypes();

                    if (type.length == 1) {
                        if (String.class.equals(type[0]) && String.class.equals(getType)) {
                            String ret = (String) mGet.invoke(obj);
                            m.invoke(obj, CommonStringUtil.trimAllSpace(ret));
                        } else if(String[].class.equals(type[0]) && String[].class.equals(getType)){
                            String[] ret = (String[]) mGet.invoke(obj);
                            m.invoke(obj, CommonStringUtil.trimAllSpace(ret));
                        } else if (List.class.equals(type[0]) && List.class.equals(getType)) {
                            List ret = (List) mGet.invoke(obj);
                            if (null != ret) {
                                for (Object o : ret) {
                                    if (o instanceof BaseBean) {
                                        removeSpaces(o);
                                    } else if (o instanceof String) {
                                        ret.set(ret.indexOf(0),CommonStringUtil.trimAllSpace(o.toString()));
                                    }
                                }
                            }
                            //isAssignableFrom方法判断A是否是B的父类或者和B类型相同或者B实现了A接口.是类与类之间的比较。
                        } else if (BaseBean.class.isAssignableFrom(type[0]) &&
                                BaseBean.class.isAssignableFrom(getType)){
                            Object oGetBaseBean = mGet.invoke(obj);
                            removeSpaces(oGetBaseBean);
                        }
                    }
                }catch (Exception e) {
                    LogUtil.doSkip();
                }
            }
        }
    }

    /**
     * 消除String对象属性的前后半角空格
     * @param escapes
     * @throws NoSuchMethodException
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    public void removeSpaces(String... escapes) throws NoSuchMethodException {
        //获取全部方法
        //getMethods()方法获取的是所有的public的函数，包括父类继承而来的
        //getDeclaredMethods()获取的是所有该类自己声明的方法
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m : methods) {
            String methodName = m.getName();
            //set方法
            if (methodName.startsWith("set")) {
                try {
                    //获取方法对应的属性名
                    String propName = methodName.substring(Constant.number_3);
                    boolean bfound = false;
                    for (String s : escapes) {
                        if (s.equalsIgnoreCase(propName)) {
                            bfound = true;
                        }
                        if (bfound) {
                            continue;
                        }

                        Method mGet = null;
                        try {
                            mGet = this.getClass().getMethod("get" + propName);
                        } catch (Exception e) {
                            continue;
                        }
                        if (null == mGet) {
                            continue;
                        }

                        //get方法返回类型
                        Class<?> getType = mGet.getReturnType();
                        //set方法参数类型
                        Class<?>[] type = m.getParameterTypes();

                        if (type.length == 1) {
                            if (String.class.equals(type[0]) && String.class.equals(getType)) {
                                String ret = (String) mGet.invoke(this);
                                m.invoke(this, CommonStringUtil.trimAllSpace(ret));
                            } else if (List.class.equals(type[0]) && List.class.equals(getType)) {
                                List ret = (List) mGet.invoke(this);
                                if (null != ret) {
                                    for (Object o : ret) {
                                        if (o instanceof BaseBean) {
                                            removeSpaces(o);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }catch(Exception e) {
                    LogUtil.doSkip();
                }
            }
        }
    }
}
