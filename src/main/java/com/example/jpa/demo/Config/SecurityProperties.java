package com.example.jpa.demo.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "evolutionary.security")
public class SecurityProperties {

    private BrowerProperties brower = new BrowerProperties();

}