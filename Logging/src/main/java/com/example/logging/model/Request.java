package com.example.logging.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private String uid;
    private String operationUid;
    private String systemName;
    private String systemTime;
    private String source;
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
}
