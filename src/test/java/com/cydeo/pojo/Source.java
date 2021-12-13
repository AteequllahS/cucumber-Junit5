package com.cydeo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {
 *     "id": null,
 *      "name": "KMBC Kansas City"
 *  }
 */
@Getter @Setter
@NoArgsConstructor
@ToString
public class Source {

    private String id ;
    private String name ;

}
