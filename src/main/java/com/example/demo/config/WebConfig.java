package com.example.demo.config;



import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
    }
    
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
	   /*
	   @Bean
	   public MessageSource messageSource() {
	       ReloadableResourceBundleMessageSource messageSource
	         = new ReloadableResourceBundleMessageSource();
	       
	       messageSource.setBasename("classpath:messages");
	       messageSource.setDefaultEncoding("UTF-8");
	       return messageSource;
	   }
	   */
	   // multi-language start
		@Bean("messageSource")
		public ResourceBundleMessageSource  messageSource() {
		    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		    messageSource.setBasenames("language/messages");
		    messageSource.setDefaultEncoding("UTF-8");
		    return messageSource;
		}
		
		@Bean
		public LocaleResolver localeResolver() {
	//	    SessionLocaleResolver slr = new SessionLocaleResolver();
		    CookieLocaleResolver clr = new CookieLocaleResolver();
		    clr.setDefaultLocale(new Locale("ko"));
		    clr.setCookieName("localeLanguage");
		    
		    //slr.setLocaleAttributeName("current.locale");
		    //slr.setTimeZoneAttributeName("current.timezone");
		    return clr;
		}
		
		@Bean
		public LocaleChangeInterceptor localeChangeInterceptor() {
		    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		    localeChangeInterceptor.setParamName("language");
		    return localeChangeInterceptor;
		}
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		    registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/*");
		}
		 // multi-language end
	   
	   @Bean
	   public LocalValidatorFactoryBean getValidator() {
	       LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	       bean.setValidationMessageSource(messageSource());
	       return bean;
	   }
	   
	   @Bean
	   public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
	       final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	       templateEngine.setTemplateResolver(templateResolver);
	       templateEngine.addDialect(sec); // Enable use of "sec"
	       return templateEngine;
	   }
	   
	   @Bean
	   public CommonsMultipartResolver multipartResolver() {
	       CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	       resolver.setMaxUploadSize(10000000);
	       return resolver;
	   }
	  //https://viblo.asia/p/set-up-swagger-2-voi-spring-rest-api-djeZ1zj3lWz 
	    @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
}