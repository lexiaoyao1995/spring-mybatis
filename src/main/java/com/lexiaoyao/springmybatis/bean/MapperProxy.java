package com.lexiaoyao.springmybatis.bean;

import com.lexiaoyao.springmybatis.annos.Select;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过动态代理生成mapper的实体类
 * 实现FactoryBean方法，用于配置到ImportBeanDefinitionRegistrar
 */
public class MapperProxy implements FactoryBean<Object> {

    private Class<?> clazz;

    /**
     * 将需要代理的接口通过参数传递进来
     *
     * @param clazz
     */
    public MapperProxy(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object getObject() throws Exception {
        //动态代理接口
        return Proxy.newProxyInstance(MapperProxy.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                return method.getAnnotation(Select.class).value();
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}