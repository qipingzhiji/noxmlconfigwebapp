package com.springmvc.config.others;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhang_htao on 2019/9/22.
 */
@Configuration
@MapperScan(basePackages = {"com.swagger2.dao"})
@PropertySource("classpath:dataSource.properties")
public class MybatisConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(environment.getProperty("driverClassName"));
        druidDataSource.setUrl(environment.getProperty("url"));
        druidDataSource.setUsername(environment.getProperty("user-name"));
        druidDataSource.setPassword(environment.getProperty("user-password"));
        return druidDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        /*mybatis全局配置*/
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        /*开启驼峰命名配置*/
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        //设置数据源
        sessionFactory.setDataSource(dataSource());
        //设置mapper.xml文件扫描路径
        String baseLocations = new ClassPathResource("mybatis/mappings/").getURL().getPath();
        File baseDirectorys = new File(baseLocations);

        LinkedList<File> listFiles = (LinkedList) FileUtils.listFiles(baseDirectorys, new String[]{"xml"}, true);

        List<ClassPathResource> collect = listFiles.stream().map((file) -> {
            return new ClassPathResource("mybatis/mappings/" + file.getName());
        }).collect(Collectors.toList());
        ArrayList<Resource> mapperLocations = new ArrayList<>();
        Resource[] resources = collect.toArray(new Resource[collect.size()]);
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }
}
