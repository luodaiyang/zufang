package com.xust.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 10:13 2019/4/23
 * @modified By:
 */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    @Value("${spring.thymeleaf.cache}")
    private  boolean thymeleafCacheEnable=true;

    private ApplicationContext  applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     *
     * 静态资源加载
     * @author Luo Daiyang
     * @date 2019/4/23 10:22
     * //@param [registry]
     * @return void
     */
   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/");

   }

   /**
    *
    * 模板资源解析器
    * @author Luo Daiyang
    * @date 2019/4/23 10:29
    * //@param []
    * @return org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver
    */
    @Bean
    @ConfigurationProperties(prefix = "spring.thymeleaf")
    public SpringResourceTemplateResolver templateResolver(){
       SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
       templateResolver.setApplicationContext(this.applicationContext);
       templateResolver.setCharacterEncoding("UTF-8");
       templateResolver.setCacheable(thymeleafCacheEnable);
       return  templateResolver;
    }



    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        //支持Sring EL表达式
        templateEngine.setEnableSpringELCompiler(true);


        return  templateEngine;

    }

    public ThymeleafViewResolver viewResolver(){

        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
         viewResolver.setTemplateEngine(templateEngine());
         return  viewResolver;
    }





}
