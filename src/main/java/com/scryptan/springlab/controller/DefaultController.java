package com.scryptan.springlab.controller;

import com.scryptan.springlab.model.Request;
import com.scryptan.springlab.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request){
        Response response = Response
                .builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code(HttpStatus.OK.getReasonPhrase())
                .errorCode("")
                .errorMessage("")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}