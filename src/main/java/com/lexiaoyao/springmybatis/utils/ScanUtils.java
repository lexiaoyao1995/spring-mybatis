package com.lexiaoyao.springmybatis.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;


public class ScanUtils {

    private static final String RESOURCE_PATTERN = "/**/*.class";

    /**
     * 可以获取指定路径下的带有某个注解的全部类
     *
     * @param path      指定路径
     * @param annoClass 指定要寻找的注解
     * @return
     */
    public static Map<String, Class> getMap(String path, Class<? extends Annotation> annoClass) {
        Map<String, Class> handlerMap = new HashMap<String, Class>();

        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(path) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                Annotation annotation = clazz.getAnnotation(annoClass);
                if (annotation != null) {
                    //将注解中的类型值作为key，对应的类作为 value
                    handlerMap.put(classname, clazz);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return handlerMap;
    }

}