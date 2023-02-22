package com.example.logging;

import com.example.logging.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @PostMapping("log")
    public void log(@RequestBody Request request){
        log.info(request.toString());
    }
}
