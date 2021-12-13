package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * {
 *         "char_id": 1,
 *         "name": "Walter White",
 *         "occupation": [
 *             "High School Chemistry Teacher",
 *             "Meth King Pin"
 *         ],
 *         "status": "Presumed dead",
 *         "nickname": "Heisenberg",
 *         "appearance": [
 *             1,
 *             2,
 *             3,
 *             4,
 *             5
 *         ],
 *      }
 */
@Getter @Setter
@NoArgsConstructor
@ToString

// ignore any unknown json fields
@JsonIgnoreProperties(ignoreUnknown = true)

public class Character {

    @JsonProperty("char_id")  // telling jackson to match json field char_id to java field id below
    private int id ;
    private String name ;
    private String[] occupation ; // you can do list or array , by choice we did array
    private String status ;
    private String nickname ;
    private List<Integer> appearance ; // you can do list or array , by choice we did list

}
