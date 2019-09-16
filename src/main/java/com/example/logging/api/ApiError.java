package com.example.logging.api;

import com.example.logging.model.Entity;
import com.example.logging.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiError {

    @Autowired
    private ErrorService errorService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/post")
    public String get(@RequestBody final Entity entity) {
        return errorService.getName(entity);
    }

}
