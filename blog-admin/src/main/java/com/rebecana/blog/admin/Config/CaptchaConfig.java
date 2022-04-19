package com.rebecana.blog.admin.Config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CaptchaConfig {

    @Bean
    public Producer captcha(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.images.width", "100");
        properties.setProperty("kaptcha.images.height", "25");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789QWEERTYUIOPasdfghjklZXCVBNM");
        properties.setProperty("kaptcha.textproducer.char.length", "4");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

