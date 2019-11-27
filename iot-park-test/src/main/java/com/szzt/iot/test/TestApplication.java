package com.szzt.iot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


/**
 * robot-test
 *
 * @author
 */
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@ComponentScan("com.szzt.iot")
@EnableWebSocket
public class TestApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}