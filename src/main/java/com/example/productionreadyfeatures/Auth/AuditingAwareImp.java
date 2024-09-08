package com.example.productionreadyfeatures.Auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditingAwareImp implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        get security
//        get authentication
//        get principle
//        get userName
        return Optional.of("MD Swaley"); //we initially pass default name .
//        means for now all the changes and creation by MD Swaley.
//        this is hard code.
    }
}
