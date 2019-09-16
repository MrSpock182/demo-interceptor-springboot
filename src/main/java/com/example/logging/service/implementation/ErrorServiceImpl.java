package com.example.logging.service.implementation;

import com.example.logging.exception.InternalServerErrorException;
import com.example.logging.model.Entity;
import com.example.logging.service.ErrorService;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {

    public String getName(Entity entity) {

        if("vamos".equalsIgnoreCase(entity.getName())) {
            throw new InternalServerErrorException("Error");
        }

        return entity.getName();
    }

}
