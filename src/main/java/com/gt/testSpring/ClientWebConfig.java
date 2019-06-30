package com.gt.testSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
public class ClientWebConfig implements WebMvcConfigurer {
 
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
    	this.applicationContext = applicationContext;
    }
	
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/index");
   }
   
   @Bean
   @Description("Thymeleaf View Resolver")
   public ThymeleafViewResolver viewResolver() {
       ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
       viewResolver.setTemplateEngine(templateEngine());
       viewResolver.setOrder(1);
       return viewResolver;
   }
   @Bean
   @Description("Thymeleaf Template Resolver")
   public SpringResourceTemplateResolver templateResolver() {
       SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
       resolver.setApplicationContext(applicationContext);
       resolver.setPrefix("/WEB-INF/views/");
       resolver.setSuffix(".html");
       resolver.setTemplateMode("HTML5");
       return resolver;
   }
    
   @Bean
   @Description("Thymeleaf Template Engine")
   public SpringTemplateEngine templateEngine() {
       SpringTemplateEngine templateEngine = new SpringTemplateEngine();
       templateEngine.setTemplateResolver(templateResolver());
       templateEngine.setTemplateEngineMessageSource(messageSource());
       return templateEngine;
   }
   
   @Bean
   @Description("Spring Message Resolver")
   public ResourceBundleMessageSource messageSource() {
       ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
       messageSource.setBasename("messages");
       return messageSource;
   }
}