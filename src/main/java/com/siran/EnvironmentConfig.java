package com.siran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by guangrongyang on 17/6/8.
 */
@PropertySource("classpath:application.properties")
@Component
public class EnvironmentConfig {
    @Autowired
    private Environment env;

    public  String getProperty(final String name){
        return env.getProperty(name);
    }

}
