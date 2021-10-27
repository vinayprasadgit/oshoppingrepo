package com.techwave.cartRelation.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.techwave.cartRelation.LowerCaseClassNameResolver;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM,property = "error",visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiResponse {

    private HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String description;

//    private ApiResponse()
//    {
//        timestamp = LocalDateTime.now();
//    }

    public ApiResponse(HttpStatus status, String message, String description) {
      //  this();
        this.status = status;
        this.message = message;
        this.description = description;
    }
}
