package com.xhMall.global;

import com.xhMall.common.constant.Constant;
import com.xhMall.common.util.LogUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by sheting on Administrator
 * DateTime  2018/10/6,14:36
 */
public final class StartUpServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        LogUtil.debug(Constant.SYSTEM_START_BEGIN);
        try {
            super.init();
            GlobalManager.init();
        }catch (Exception e) {
            LogUtil.error(Constant.SYSTEM_START_FAIL,e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        GlobalManager.destroy();
    }


}
