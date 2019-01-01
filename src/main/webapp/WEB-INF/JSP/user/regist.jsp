<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>
        梦商城
    </title>
    <link href="/assets/demo/default/base/style.bundle.css" rel="stylesheet" media="screen" />
   <%-- <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen" />--%>
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body class="m--skin- m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default"  >
<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--hor m-login m-login--singin m-login--2 m-login-2--skin-1" id="m_login" style="background-image: url(/assets/app/media/img/bg/bg-1.jpg);">
        <div>
            <input type="button" id="regist" value="注册" class="btn btn-focus" style="float: right;background: transparent;border:none;text-decoration: underline">
        </div>
        <div class="m-grid__item m-grid__item--fluid	m-login__wrapper">
            <div class="m-login__container" id="refresh_html">
                <div class="m-login__signin">
                    <div class="m-login__head">
                        <h3 class="m-login__title">
                            注册
                        </h3>
                    </div>
                    <form class="m-login__form m-form">
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="姓名" id="fullname">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="电子邮箱" id="email" autocomplete="off">
                        </div>
                        <%--性别--%>
                        <div class="form-group m-form__group" >
                            <select id="sex" class=""form-control m-input>
                                <option value="1">
                                    男
                                </option>
                                <option value="2">
                                    女
                                </option>
                            </select>
                        </div>
                        <%--生日--%>

                        <div class="form-group">
                            <div id="pickTime" class="form-group input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input class="form-control m-input"  type="text" value="" readonly="">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <input type="hidden" id="dtp_input2" value=""><br>
                        </div>

                        <%--手机号码--%>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="手机号码" id="mobilePhoneNum">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control" type="password" placeholder="密码" id="password">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input m-login__form-input--last" type="password" placeholder="确认密码" id="rpassword">
                        </div>
                        <div class="form-group m-form__group">
                            <input class="form-control m-input" type="text" placeholder="验证码" id="verifyCode">
                        </div>
                        <div class="form-group m-form__group" >
                            <div style="float:right;display:inline-block">
                                <img src="/user/getVerifyCode.do" height="6%" id="img" class="col-md-7">
                                <a href="javascript:;" style="clear: both;color: red;" onclick="changeImg();">看不清？</a>
                            </div>
                        </div>
                        <div class="m-login__form-action" style="clear: both">
                            <button  class="btn m-btn m-btn--pill m-btn--custom m-btn--air m-login__btn" id="userRegist">
                                注册
                            </button>
                            &nbsp;&nbsp;
                            <button id="m_login_signup_cancel" class="btn m-btn m-btn--pill m-btn--custom m-btn--air m-login__btn">
                                取消
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/common.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

<script>
    $(document).ready(function() {
        $("#userRegist").click(function () {
            regist();
        });


        $('#pickTime').click(function () {
            pickDatetime();
        })
    });

    function regist() {
        var url="/user/regist.do";
        var data = getParam();
        debugger;
        Base.ajax("POST",url,data,function(result) {
            alert("success");
            return ;
        });
    }


    function getParam() {
        debugger;
        var data = {
            username : $('#fullname').val(),
            sexy : $("input[name='sexy']:checked").val(),
            email : $('#email').val(),
            birthDate : $('#birthday').val(),
            mobileNumber : $('#mobilePhoneNum').val(),
            password : $('#password').val(),
            comfirmPassword : $('#rpassword').val(),
            verifyCode :$('#verifyCode').val()
        }
        return data;
    }

    function changeImg() {
        debugger;
        var url="/user/getVerifyCode.do?time="+new Date().getTime();
        $("#img").prop("src",url);
    }

    function pickDatetime() {
        $('.form_date').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            format: 'yyyy年MMdd日',
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            minView: 2
        });
    }


</script>