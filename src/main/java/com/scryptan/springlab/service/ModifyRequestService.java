package com.scryptan.springlab.service;

import com.scryptan.springlab.model.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.Date;

@Service
@Qualifier("ModifyRequestService")
public class ModifyRequestService implements IModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSystemTime(DateFormat.getDateTimeInstance().format(new Date()));

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:9999/log",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
