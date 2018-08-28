package com.ajax.shop.config;

import com.ajax.shop.web.filter.SessionManagementFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * 10.06.18
 */
@Configuration
public class AppConfig {
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE - 1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean registerRequestLogFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new SessionManagementFilter());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


    @Autowired
    public void mbeanExporter(AnnotationMBeanExporter mBeanExporter) {
        mBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
    }
}
