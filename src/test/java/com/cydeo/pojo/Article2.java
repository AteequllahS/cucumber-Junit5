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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

// Create a POJO class to represent Article
// required fields : source , author , title
@Getter @Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article2 {

    // source json field value is another json object
    // so we can either use map or use another pojo to represent it
    private Source source ;
    private String author ;
    private String title ;

}
