package com.example.codeE.judge.configurations;
import com.example.codeE.judge.JudgeList;
import com.example.codeE.judge.handlers.SpringBootHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringBootHandlerConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SpringBootHandler springBootHandler(JudgeList judges){
        SpringBootHandler springBootHandler = new SpringBootHandler(judges);
        return springBootHandler;
    }
}
