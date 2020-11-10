package com.lexiaoyao.springmybatis.config;

import com.lexiaoyao.springmybatis.annos.Mapper;
import com.lexiaoyao.springmybatis.annos.MapperScan;
import com.lexiaoyao.springmybatis.bean.MapperProxy;
import com.lexiaoyao.springmybatis.mapper.OneMapper;
import com.lexiaoyao.springmybatis.utils.ScanUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 实现ImportBeanDefinitionRegistrar的registerBeanDefinitions方法
 * 可以通过构造beanDefinition来注入bean
 * AnnotationMetadata importingClassMetadata这个变量可以获取主类上的所有注解
 */
@Component
public class BeanRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName());
        String basePackage = (String) annotationAttributes.get("value");

        Map<String, Class> map = ScanUtils.getMap(basePackage, Mapper.class);
        for (Class clazz : map.values()) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            beanDefinition.setBeanClassName(MapperProxy.class.getName());
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazz);
            registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
        }


    }
}