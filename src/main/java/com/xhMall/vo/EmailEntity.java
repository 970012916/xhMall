package com.xhMall.vo;

import com.xhMall.db.entity.base.BaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailEntity extends BaseEntity {

    private static final long serialVersionUID = 3153932931477519453L;


    @Value("#{configProperties['mail.username']}")
    private String username;

    @Value("#{configProperties['mail.password']}")
    private String password;

    @Value("#{configProperties['mail.host']}")
    private String domain;

    @Value("#{configProperties['mail.from']}")
    private String from;

    public String getFrom() {
        return from;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomain() {
        return domain;
    }
}
