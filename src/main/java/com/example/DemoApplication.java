package com.example;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.MultipartConfigElement;

@SpringBootApplication
public class DemoApplication {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public static SecretKey key = Jwts.SIG.HS256.key().build();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/uploads/**")
                        .addResourceLocations("file:" + uploadDir);
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Cho phép tất cả các URL
                        .allowedOriginPatterns("*") // Cho phép tất cả các nguồn
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Cho phép tất cả các phương thức HTTP
                        .allowedHeaders("*") // Cho phép tất cả các headers
                        .allowCredentials(true); // Cho phép gửi thông tin xác thực (nếu cần)
            }
        };
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(50)); // Giới hạn kích thước file upload là 50MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(50)); // Giới hạn kích thước request là 50MB
        return factory.createMultipartConfig();
    }
}
