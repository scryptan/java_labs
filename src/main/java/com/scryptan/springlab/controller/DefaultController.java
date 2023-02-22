package com.scryptan.springlab.controller;

import com.scryptan.springlab.model.Request;
import com.scryptan.springlab.model.Response;
import com.scryptan.springlab.service.IModifyRequestService;
import com.scryptan.springlab.service.IModifyResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j()
@RestController
public class DefaultController {

    private final IModifyResponseService modifyUid;
    private final IModifyResponseService modifyCode;
    private final IModifyRequestService modifyRequestTime;

    @Autowired
    public DefaultController(@Qualifier("ModifyResponseUid") IModifyResponseService modifyUid,
                             @Qualifier("ModifyResponseCode") IModifyResponseService modifyCode,
                             @Qualifier("ModifyRequestService") IModifyRequestService modifyRequestTime) {
        this.modifyUid = modifyUid;
        this.modifyCode = modifyCode;
        this.modifyRequestTime = modifyRequestTime;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code(HttpStatus.OK.getReasonPhrase())
                .errorCode("")
                .errorMessage("")
                .build();

        modifyRequestTime.modify(request);

        log.info(String.format("Response before modify %s", response));

        modifyUid.modify(response);
        modifyCode.modify(response);

        log.warn(String.format("Response after modify %s", response));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}