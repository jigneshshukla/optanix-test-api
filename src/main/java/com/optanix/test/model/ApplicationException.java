package com.optanix.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jignesh
 * This class represents API Level exception
 */
@ApiModel(description = "Model object representing API level user defined exception")
@Data
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    @ApiModelProperty(dataType = "Integer", value = "HTTP Status Code.")
    private int status;

    @ApiModelProperty(dataType = "String", value = "API Level User Defined error key which can be used by consumers to identify distinct reason for the error.")
    private String code;

    @ApiModelProperty(dataType = "Map", position = 6, value = "A Map of key/value pair providing additional details about data/input elements associated with request.")
    private Map<String, String> parameters = new HashMap<>();

    public ApplicationException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public ApplicationException(int status, String code, String message, Throwable ex) {
        super(message, ex);
        this.status = status;
        this.code = code;
    }

    protected void addParameter(String key, String value) {
        parameters.put(key, value);
    }

}
