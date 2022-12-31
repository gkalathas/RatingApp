package com.example.frontToBack.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {


    private float stars = 0.0F;

    @OneToOne
    @NotBlank
    private Feedback feedback;
}
