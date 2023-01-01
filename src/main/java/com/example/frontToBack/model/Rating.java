package com.example.frontToBack.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {


    private float stars = 0.0F;

    @OneToOne
    private Feedback feedback;
}
