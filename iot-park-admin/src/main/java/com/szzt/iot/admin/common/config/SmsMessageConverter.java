package com.szzt.iot.admin.common.config;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

public class SmsMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
       return session.createObjectMessage((Serializable) object);
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        System.out.println(message);
        ObjectMessage objMessage = (ObjectMessage) message;
        return objMessage.getObject();
    }
}
