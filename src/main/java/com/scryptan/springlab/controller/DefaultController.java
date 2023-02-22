package com.scryptan.springlab.controller;

import com.scryptan.springlab.model.Request;
import com.scryptan.springlab.model.Response;
import com.scryptan.springlab.service.IModifyService;
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

    private final IModifyService modifyUid;
    private final IModifyService modifyCode;

    @Autowired
    public DefaultController(@Qualifier("ModifyUid") IModifyService modifyUid, @Qualifier("ModifyCode") IModifyService modifyCode) {
        this.modifyUid = modifyUid;

        this.modifyCode = modifyCode;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {
        Response response = Response
                .builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code(HttpStatus.OK.getReasonPhrase())
                .errorCode("")
                .errorMessage("")
                .build();

        log.info(String.format("Response before modify %s", response));

        modifyUid.modify(response);
        modifyCode.modify(response);

        log.warn(String.format("Response after modify %s", response));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}