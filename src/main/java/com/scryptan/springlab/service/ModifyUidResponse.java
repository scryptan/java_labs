package com.scryptan.springlab.service;

import com.scryptan.springlab.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ModifyResponseUid")
public class ModifyUidResponse implements IModifyResponseService {
    @Override
    public Response modify(Response response) {

        response.setUid("modified uid");

        return response;
    }
}
