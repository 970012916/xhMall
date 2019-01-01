package com.xhMall.global.managerInterface;

import com.xhMall.exception.PrjException;

/**全局管理对象的接口
 * Created by sheting on Administrator
 * DateTime  2018/9/19,22:38
 */
public interface IGlobalManager {
    /**
     * 初始化
     * @throws PrjException
     */
    void init() throws PrjException;

    /**
     * 释放
     */
    void destroy();
}
