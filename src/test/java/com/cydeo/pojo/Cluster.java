package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cluster {

    private int id;
    private String name;
    @JsonProperty("rooms")
    private List<Room> roomList;
}
