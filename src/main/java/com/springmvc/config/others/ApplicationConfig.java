package com.springmvc.config.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Locale;

/**
 * Created by zhang_htao on 2019/9/22.
 */
@Configuration
@EnableTransactionManagement
@EnableAsync
@EnableAspectJAutoProxy
@EnableScheduling
@Import({MybatisConfig.class})
@ComponentScan(basePackages = {"com.swagger2.service"})
public class ApplicationConfig {

    private Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    /**
     *
     */
    public ApplicationConfig()
    {
        // TODO Auto-generated constructor stub
        logger.info("[Initialize application]");
        Locale.setDefault(Locale.CHINA);
    }
}
