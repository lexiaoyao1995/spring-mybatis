package com.lexiaoyao.springmybatis;

import com.lexiaoyao.springmybatis.annos.MapperScan;
import com.lexiaoyao.springmybatis.config.BeanRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 实现mybatis整合spring的大致思路
 * 主要思路为
 * 1、通过jdk动态代理生成接口mapper的代理类
 * 2、将代理类注入到容器
 * 2.1 采用BeanFactory的方式，工厂化生成代理类
 * 2.2 采用ImportBeanDefinitionRegistrar，直接定义BeanDefinition来构造bean
 * 3、指定包扫描注解（ScanUtils）
 * 测试方法在com.lexiaoyao.springmybatis.SpringMybatisApplicationTests
 */
@SpringBootApplication
@Import(BeanRegister.class)//需要通过Import注解来导入BeanRegister
@MapperScan("com.lexiaoyao.springmybatis")
public class SpringMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisApplication.class, args);
    }

}
