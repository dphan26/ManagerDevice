package com.example.demo.config;



import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer  {

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ClassLoaderTemplateResolver templateResolver() {
    //	http://zetcode.com/articles/springbootthymeleafconf/
        ClassLoaderTemplateResolver tres = new ClassLoaderTemplateResolver();

     //   tres.setPrefix("WEB-INF/views/");
        tres.setPrefix("templates/");
        tres.setSuffix(".html");        
        tres.setCacheable(false);
       
        tres.setTemplateMode("HTML5");
        tres.setCharacterEncoding("UTF-8");
        
        return tres;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver() {

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setCache(false);
        viewResolver.setOrder(1);

        return viewResolver;
    }    

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("home/index");
//    }
    
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
	//https://viblo.asia/p/su-dung-modelmapper-trong-spring-boot-63vKj1Vd52R
	   @Bean
	    public ModelMapper modelMapper() {
	        // Tạo object và cấu hình
	        ModelMapper modelMapper = new ModelMapper();
	        modelMapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.STRICT);
	        return modelMapper;
	    }
	   //https://www.baeldung.com/spring-custom-validation-message-source
	   @Bean
	   public MessageSource messageSource() {
	       ReloadableResourceBundleMessageSource messageSource
	         = new ReloadableResourceBundleMessageSource();
	       
	       messageSource.setBasename("classpath:messages");
	       messageSource.setDefaultEncoding("UTF-8");
	       return messageSource;
	   }
	   
	   @Bean
	   public LocalValidatorFactoryBean getValidator() {
	       LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	       bean.setValidationMessageSource(messageSource());
	       return bean;
	   }
}