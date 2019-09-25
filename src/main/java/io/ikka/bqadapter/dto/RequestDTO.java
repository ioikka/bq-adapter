package io.ikka.bqadapter.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RequestDTO {
    private String className;
    private String action;
    private Map<String, String> fields;
}
