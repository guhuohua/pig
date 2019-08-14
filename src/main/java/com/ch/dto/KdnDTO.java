package com.ch.dto;

import lombok.Data;

import java.util.List;

@Data
public class KdnDTO {
    private String LogisticCode;
    private String ShipperCode;
    private List<Traces> Traces;
    private String State;
    private String EBusinessID;
    private boolean Success;
}
