package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Category {


    private String id;

    @JsonProperty("name")
    private String categoryName;


}
