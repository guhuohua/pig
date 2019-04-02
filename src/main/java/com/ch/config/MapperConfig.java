package com.ch.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    @ConditionalOnMissingBean(ModelMapper.class)
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
