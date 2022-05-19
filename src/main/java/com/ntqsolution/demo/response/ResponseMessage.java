package com.ntqsolution.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private Object data;
}
