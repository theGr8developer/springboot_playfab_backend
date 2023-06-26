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
class Current{
     @JsonProperty("weather_descriptions")
     String weatherDescriptions;
     @JsonProperty("wind_speed")
     int windSpeed;
     int temperature;
     String observation_time;
}

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class WeatherEntity {
    
   
 private Current current;
    

}
