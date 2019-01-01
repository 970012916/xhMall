package com.xhMall.controller;

import com.xhMall.common.constant.ResultType;
import com.xhMall.common.constant.UserAccountStatus;
import com.xhMall.common.util.CommonJettUtil;
import com.xhMall.common.util.CommonStringUtil;
import com.xhMall.common.util.VerifyCodeUtil;
import com.xhMall.db.entity.EmployeeEntity;
import com.xhMall.db.entity.MenuEntity;
import com.xhMall.db.entity.UserEntity;
import com.xhMall.dto.BaseResult;
import com.xhMall.exception.MallException;
import com.xhMall.service.UserService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    private int a = 0;

    /**
     * @Author: 零度亲吻gy
     * @Description: 注册
     * @Date: 15:39 2018/3/17
     * @param userEntity
     */
    @RequestMapping(value = "/regist")
    @ResponseBody
    public BaseResult<?> regist(HttpServletResponse response, HttpServletRequest request , UserEntity userEntity, BindingResult bindingResult ) {

        BaseResult<String> result = new BaseResult<String>();
        try {
           StringBuffer sb = new StringBuffer();
            if(bindingResult.hasErrors()) {
                List<FieldError> list = bindingResult.getFieldErrors();
                for (FieldError fieldError : list) {
                    sb.append(fieldError.getDefaultMessage());
                }
                result.setData(sb.toString());
                result.setStatus(ResultType.FAILURE);
                return result;
            }
            //判断用户名是否注册
            UserEntity existingUserEntity = userService.selectByUsername(CommonStringUtil.trim(userEntity.getUsername()));
            if(null != existingUserEntity ) {
                result.setStatus(ResultType.FAILURE);
                result.setMessage("用户名已存在");
                return result;
            }
            //生成用户的唯一id
            String userId = getUUID();
            userEntity.setId(userId);
            //获取session中的验证码
            String verifyCode = getVerifyCodeInSession(request,response);
            userEntity.setVerifyCode(verifyCode);
            //设置用户的状态 0-不可用1-未激活2-可用
            userEntity.setStatus(UserAccountStatus.UNACTIVE);

            //判断两次输入的密码是否一致
            String password = userEntity.getPassword();
            String comfirmPassword = userEntity.getComfirmPassword();
            if(! password.equals(comfirmPassword)) {
                result.setStatus(ResultType.FAILURE);
                result.setData("两次输入的密码不一致");
            }

            //密码md5加签
            password = DigestUtils.md5DigestAsHex((password + userEntity.getVerifyCode()).getBytes());
            userEntity.setPassword(password);
            //去掉注册字段首尾的空格
            userEntity = (UserEntity) CommonStringUtil.trim(userEntity);
            userService.regist(userEntity);
        } catch (Exception e) {
           e.printStackTrace();
        } finally{
            request.getSession().removeAttribute("verifyCode");
        }
        return  result;
    }

    /**
     * @Author: 零度亲吻gy
     * @Description: 跳转注册页面
     * @Date: 15:39 2018/3/17
     * @param
     */
    @RequestMapping(value = "/mall_regist")
    public ModelAndView mall_regist (HttpServletRequest request , HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/regist");
        return modelAndView;
    }


    /**
     * @Author: 零度亲吻gy
     * @Description: 获取验证码
     * @Date: 15:40 2018/3/17
     * @param
     */
    @RequestMapping(value = "/getVerifyCode",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<?> getVerifyCode(HttpServletRequest request , HttpServletResponse response) {
        BaseResult<byte[]> result = null;
        try {
            result = new BaseResult<byte[]>();
            String verifyCode = VerifyCodeUtil.generateVerifyCode();
            setVerifyCodeInSession(request, response, verifyCode);
            byte[] verifyCodeByte = VerifyCodeUtil.outputImage(verifyCode);
            result.setStatus(ResultType.SUCCESS);
            response.getOutputStream().write(verifyCodeByte);
        } catch (Exception e) {
            result.setMessage("输出验证码错误");
            result.setStatus(ResultType.FAILURE);
        }
        return result;
    }

    /**
     * @Author: 零度亲吻gy
     * @Description: 用户登录
     * @Date: 15:06 2018/3/3
     * @param userEntity
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest reruest,HttpServletResponse response,UserEntity userEntity) {
        BaseResult<String> result = new BaseResult<String>();
        try {
            if(CommonStringUtil.isNullOrEmpty(userEntity.getUsername())) {
                throw new MallException("请输入用户名和密码");
            }
            if(CommonStringUtil.isNullOrEmpty(userEntity.getPassword())) {
                throw new MallException("请输入用户名和密码");
            }
            String username = userEntity.getUsername();
            UserEntity checkedUser = userService.selectByUsername(username);

            String password = userEntity.getPassword();
            String verifyCode = checkedUser.getVerifyCode();
            password = DigestUtils.md5DigestAsHex((password + verifyCode).getBytes());
            if(password.equals(checkedUser.getPassword())) {
                result.setStatus(ResultType.SUCCESS);
            }

            //登录时设置自动登陆
            if(userEntity.getAutoLoginFlag() == null || userEntity.getAutoLoginFlag() == UserAccountStatus.NOT_AUTO_LOGIN) {
                userEntity.setAutoLoginFlag(UserAccountStatus.NOT_AUTO_LOGIN);
            }

            //设置自动登录的cookie
            if (userEntity.getAutoLoginFlag() == 1) {
                List<Cookie> list = setCookie(username,password);
                list.forEach(cookie -> response.addCookie(cookie));
            }
            setCurrentUserInfoInSession(reruest,response,checkedUser);
        }catch (Exception e) {
            result.setStatus(ResultType.FAILURE);
            result.setMessage(e.getMessage());
        }
        return "index";

    }

    /**
     * @Author: 零度亲吻gy
     * @Description: 激活用户d
     * @Date: 15:47 2018/3/3
     * @param
     */
    @RequestMapping(value = "/activate",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<?> activate(String username, String validateCode) {
        BaseResult<String> result = new BaseResult<String>();
        try {
            if(CommonStringUtil.isNullOrEmpty(username)) {
                throw new MallException("非法链接");
            }
            if(CommonStringUtil.isNullOrEmpty(validateCode)) {
                throw new MallException("非法链接");
            }
            userService.updateUserByUsername(username,validateCode);
            result.setStatus(ResultType.SUCCESS);

        }catch (Exception e) {
            result.setStatus(ResultType.FAILURE);
            result.setMessage(e.getMessage());
        }
        return null;
    }


    /**
     * @Author: 零度亲吻gy
     * @Description: 设置cookie，自动登陆
     * @Date: 16:44 2018/3/17
     * @param username ,password
     */
    public List<Cookie> setCookie(String username,String password) {
        List<Cookie> list = new ArrayList<Cookie>(2);
        Cookie usernameCookie = new Cookie("username" , username);
        list.add(usernameCookie);
        Cookie passwordCookie = new Cookie("password",password);
        list.add(passwordCookie);
        return  list;
    }

    /**
     *
     * 测试json
     */
    @RequestMapping(value = "/showData")
    @ResponseBody
    public BaseResult<?> showData() {
        BaseResult<ArrayList<MenuEntity>> responseEntity = new BaseResult <>();
        ArrayList<MenuEntity> arrayList = new ArrayList <>();
        MenuEntity menuEntity = new MenuEntity();
        arrayList.add(menuEntity);
        menuEntity.setIconPath("m-menu__link-icon flaticon-suitcase");
        menuEntity.setMenuName("Custom Pages");
        MenuEntity childMenu = new MenuEntity();
        childMenu.setMenuName("User Pages");
        MenuEntity subMenu = new MenuEntity();
        subMenu.setMenuName("Login-1");
        subMenu.setUrlPath("snippets/pages/user/login-1.html");
        ArrayList<MenuEntity> list1 = new ArrayList <>();
        list1.add(childMenu);
        menuEntity.setChildren(list1);
        ArrayList<MenuEntity> list2 = new ArrayList <>();
        list2.add(subMenu);
        childMenu.setChildren(list2);
        responseEntity.setStatus("SUCCESS");
        responseEntity.setData(arrayList);
        return responseEntity;
    }

    /**
     *
     * 测试json
     */
    @RequestMapping(value = "/showData1")
    @ResponseBody
    public String showData1() {

        return "hahah";
    }


    @RequestMapping("/index")
    public String testView(Model model) {
        model.addAttribute("item","/index");
        //return  model.addAttribute("item","index"); //这里的myView为layout.xml中配置的视图名称，根据返回值，去匹配对应的jsp页面
        return "myView";
    }

    @RequestMapping("/test")
    public ModelAndView testView() {
        return new ModelAndView("/test");
    }

    /**
     * 测试导出功能
     * ps:下载的时候不适用ajax 使用 window.location.href = url;
     * @param response
     * @return
     */
    @RequestMapping("/exportData")
    @ResponseBody
    public void exportData(HttpServletRequest request  ,HttpServletResponse response){
        OutputStream out = null;
        Workbook workbook = null;
        try {
            HashMap<String, Object> model = new HashMap<>();
            model.put("report_year",2015);
            model.put("report_month", 8);
            ArrayList<EmployeeEntity> userList = new ArrayList <EmployeeEntity>(2);
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setName("小明");
            employeeEntity.setSex("boy");
            userList.add(employeeEntity);
            EmployeeEntity employeeEntity1 = new EmployeeEntity();
            employeeEntity1.setName("xiaoHong");
            employeeEntity1.setSex("girl");
            userList.add(employeeEntity1);
            List<UserEntity> list = userService.selectAllUsers();
            model.put("userList", list);
            System.out.println("***************************" + list.size());
            workbook = CommonJettUtil.transForm("sample.xls",model);
            response.setHeader ("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding ("utf-8");//设置编码集,文件名不会发生中文乱码
            response.setHeader ("Content-Disposition", "attachment;filename=" + "test.xls");
            out = response.getOutputStream ();
            byte[] body = CommonJettUtil.asWorkbookBytes(workbook);
            out.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/fileUpLoad")
    public String fileUpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        try {
            String originalFilename = file.getOriginalFilename();//原文件名字
            InputStream is = file.getInputStream();//获取输入流
            Workbook workbook = CommonJettUtil.getWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getRow(2).getCell(0).getRichStringCellValue().toString());
        } catch (Exception e) {
        }
        return "redirect:/index.jsp";
    }
}
