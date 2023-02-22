package com.scryptan.springlab.service;

import com.scryptan.springlab.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ModifyCode")
public class ModifyCode implements IModifyService{
    @Override
    public Response modify(Response response) {
        response.setCode("modified code");
        return response;
    }
}
