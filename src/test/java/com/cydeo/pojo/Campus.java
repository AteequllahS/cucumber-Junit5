package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Campus {

    private int id;
    private String location;
    @JsonProperty("clusters")
    private List<Cluster> clusterList;

}
