package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations {

    @JsonProperty("country_id")
    private String countryId;

    @JsonProperty("location_id")
    private int locationId;

    private String city;

    private List<Map<String,String>> links;

    private List<Link> linksObject;

}
