package com.xhMall.common.util;

import com.sun.mail.util.MailSSLSocketFactory;
import com.xhMall.exception.MallException;
import com.xhMall.db.entity.UserEntity;
import com.xhMall.vo.EmailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ClassName: Sendmail
 * @Description: Sendmail类继承Thread，因此Sendmail就是一个线程类，这个线程类用于给指定的用户发送Email
 * @author: 零度亲吻gy
 * @date: 2015-1-12 下午10:43:48
 */

@Component
public class SendEmailUtil extends Thread{


    @Autowired
    private SimpleMailMessage templateMessage;
    @Autowired
    private EmailEntity emailEntity;

    public void submit(UserEntity userEntity) {
        sendEmail(userEntity);
    }

    private static final Logger log = LoggerFactory.getLogger(SendEmailUtil.class);

    //创建邮件
    private Message createEmail(Session session, UserEntity userEntity) throws AddressException, MessagingException {
        log.info("开始发验证邮件");
        //User user = deque.pollFirst();
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailEntity.getFrom()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEntity.getEmail()));
        message.setSubject("用户注册邮件");
        StringBuffer sb = new StringBuffer();
        SimpleMailMessage smm = new SimpleMailMessage(this.templateMessage);
        smm.setTo(userEntity.getEmail().trim());
        //邮件内容
        sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://127.0.0.1:8080/user/activate.do?username=");
        sb.append(userEntity.getUsername());
        sb.append("&validateCode=");
        sb.append(userEntity.getVerifyCode());
        sb.append("\">http://127.0.0.1:8080/user/activate.do?username=");
        sb.append(userEntity.getUsername());
        sb.append("&validateCode=");
        sb.append(userEntity.getVerifyCode());
        sb.append("</a>");
        message.setContent(sb.toString(),"text/html;charset=UTF-8");
        message.saveChanges();
        return message;

    }


    //发送邮件
    private void sendEmail(UserEntity userEntity){
        try{
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            prop.setProperty("mail.host", emailEntity.getDomain());
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            Authenticator auth = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    // TODO Auto-generated method stub
                    return new PasswordAuthentication(emailEntity.getUsername(), emailEntity.getPassword());
                }
            };
            Session session = Session.getInstance(prop,auth);
            session.setDebug(true);
            Transport ts = session.getTransport();
            ts.connect(emailEntity.getDomain(), emailEntity.getUsername(), emailEntity.getPassword());

            // ts.sendMessage(message, message.getAllRecipients());
            Message message = createEmail(session,userEntity);
            Transport.send(message);
            ts.close();
        }catch (Exception e) {
            log.info("发送注册邮件异常｛｝",e.getMessage(),e);
            if(null != e ) {
                throw new MallException("注册失败");
            }
        }
    }

}
