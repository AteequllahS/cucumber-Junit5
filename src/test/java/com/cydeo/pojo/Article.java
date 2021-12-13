package com.cydeo.pojo;
/**
 *       {
 *             "source": {
 *                 "id": null,
 *                 "name": "KMBC Kansas City"
 *             },
 *             "author": "KMBC 9 News Staff",
 *             "title": "Judge sets aside conviction, Kevin Strickland to be released after 43 years in prison - KMBC Kansas City",
 *            }
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

// Create a POJO class to represent Article
// required fields : source , author , title
@Getter @Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

    // source json field value is another json object
    // so we can either use map or use another pojo to represent it
    private Map<String,Object> source ;
    private String author ;
    private String title ;

}
