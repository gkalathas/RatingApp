package com.example.frontToBack.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String templateName;
    private String logoImage;
    private String mailSubject;
    private String multiPurposeUrl;

}
