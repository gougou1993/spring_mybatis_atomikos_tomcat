package com.xy.datasource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhong
 * @date 2019年7月11日 下午3:18:43
 * 
 */
//@Component
//@Aspect
public class DataSourceAspect {
	
    @Before("execution(* com.xy.dao.*.*.*(..))")
    public void intercept(JoinPoint point) throws Exception {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }
    private void resolveDataSource(Class<?> clazz, Method method) {
        String sourceName = null;
        try {
            Class<?>[] types = method.getParameterTypes();
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) DataSource.class)) {
            	
                DataSource source = clazz.getAnnotation(DataSource.class);
                sourceName = source.name();
                DynamicDataSourceHolder.setDataSource(sourceName);
            }
            Method m = clazz.getMethod(method.getName(), types);
            
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
            	
                DataSource source = m.getAnnotation(DataSource.class);
                sourceName = source.name();
                DynamicDataSourceHolder.setDataSource(sourceName);
            }
        } catch (Exception e) {
            System.out.println(clazz + ":" + e.getMessage());
        }
    }

}
