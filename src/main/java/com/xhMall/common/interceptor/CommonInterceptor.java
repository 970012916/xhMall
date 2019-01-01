package com.xhMall.common.interceptor;

import com.xhMall.db.entity.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor{


    //默认返回url
    private static final String DEFAULT_RETURN = "/";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        if(request.getRequestURI().toString().contains("user")){
            return true;
        }
        UserEntity currentUserInfo = (UserEntity) request.getSession(false).getAttribute("currentUserInfo");
        if(null == currentUserInfo) {
            response.setContentType("text/html;charset=UTF-8");

            //ajax 请求
            if (request.getHeader("x-requseted-with") != null
                    && request.getHeader("x-requseted-with").equalsIgnoreCase("XMLHttpRequest")) {
                    response.setHeader("sessionTimeOut","session is timeout,please relogin ");
                    response.setStatus(401);
            }else {
                //普通请求
                String path = request.getContextPath();
                StringBuffer basePath = new StringBuffer()
                        .append(request.getScheme())
                        .append("://")
                        .append(request.getServerName())
                        .append(":")
                        .append(request.getServerPort())
                        .append(path)
                        .append("/");
                StringBuffer responseStr = new StringBuffer()
                        .append("<html><header><script type=\"text/javascript\">")
                        .append("window.location.href=\"")
                        .append(basePath).append(DEFAULT_RETURN).append("\";")
                        .append("</script></header></html>");
                response.setHeader("sessionTimeOut","session is timeout,please relogin ");
                response.setStatus(401);
                response.getWriter().write(responseStr.toString());
            }
            request.getRequestDispatcher("/");
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
