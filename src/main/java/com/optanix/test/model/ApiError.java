package com.optanix.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author jignesh
 * This class represents domain object for Api level errors
 */
@ApiModel(description = "Model object providing API Level error details")
@Data
@AllArgsConstructor
public class ApiError {
    @ApiModelProperty(dataType = "Date", value = "Timestamp when the error occurred.")
    private Date timestamp;
    @ApiModelProperty(dataType = "Integer", position = 1, value = "HTTP status code.")
    private int status;
    @ApiModelProperty(dataType = "String", position = 2, value = "HTTP error message.")
    private String error;
    @ApiModelProperty(dataType = "String", position = 3, value = "Exception/Error message.")
    private String message;
    @ApiModelProperty(dataType = "String", position = 4, value = "A unique string constant representing the specific error/exception scenario.")
    private String code;
    @ApiModelProperty(dataType = "String", position = 5, value = "API URL/Path which resulted in error.")
    private String path;
    @ApiModelProperty(dataType = "Map", position = 6, value = "A Map of key/value pair providing additional details about data/input elements associated with request.")
    private Map<String, String> parameters;
}
