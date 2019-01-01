<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="m-login__forget-password">
        <div class="m-login__head">
            <h3 class="m-login__title">
                忘记密码 ?
            </h3>
            <div class="m-login__desc">
                请输入注册邮箱重置密码：
            </div>
        </div>
        <form class="m-login__form m-form" action="">
            <div class="form-group m-form__group">
                <input class="form-control m-input" type="text" placeholder="邮箱" name="email" id="m_email" autocomplete="off">
            </div>
            <div class="m-login__form-action">
                <button id="m_login_forget_password_submit" class="btn m-btn m-btn--pill m-btn--custom m-btn--air m-login__btn m-login__btn--primary">
                    确定
                </button>
                &nbsp;&nbsp;
                <button id="m_login_forget_password_cancel" class="btn m-btn m-btn--pill m-btn--custom m-btn--air m-login__btn">
                    取消
                </button>
            </div>
        </form>
    </div>
    <div class="m-login__account">
        <span class="m-login__account-msg">
            未拥有账户？
        </span>
        &nbsp;&nbsp;
        <a href="javascript:;" id="m_login_signup" class="m-link m-link--light m-login__account-link">
            注册
        </a>
    </div>

</body>
</html>
