package com.example.productionreadyfeatures.cofig;

import com.example.productionreadyfeatures.Auth.AuditingAwareImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.ui.ModelMap;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImp")
public class modelMapper {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean  //Now our Jpa is also attach with auditor.
    public AuditorAware<String> getAuditorAwareImp(){
        return new AuditingAwareImp();
    }

}
