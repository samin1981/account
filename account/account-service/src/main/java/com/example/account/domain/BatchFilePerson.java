package com.example.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Getter
@Setter
public class BatchFilePerson {
    private Integer id;
    private String personName;
    private String nationalCode;
}
