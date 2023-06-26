package com.flecklab.authentication.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class LocationEntity {
    @JsonProperty("country_name")
    private String countryName;

    private String city;
}
