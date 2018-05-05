package lpnu.ikta.ki.tutor.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;

@Configurable
@EnableWebMvc
@ComponentScan("lpnu.ikta.ki.tutor.*")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        //photos for project
        registry.addResourceHandler("/style/").addResourceLocations("/style/");
        registry.addResourceHandler("/newsImages/")
                .addResourceLocations("file:" + System.getProperty("user.home")
                        + File.separator + "projectOSBB" + File.separator +  "newsImages\\");
    }

    @Bean               // бін для валідації полів
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(new String[] { "classpath:validation" });
        return messageSource;
    }
}