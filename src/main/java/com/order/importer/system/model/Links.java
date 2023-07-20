package com.order.importer.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Links {

    @JsonProperty("next")
    private String next;
    @JsonProperty("self")
    private String self;
    @JsonProperty("prev")
    private String prev;

}
