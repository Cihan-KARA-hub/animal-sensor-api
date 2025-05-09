package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.VetDto;

import com.yelman.cloudserver.services.VetServices;
import com.yelman.cloudserver.services.impl.VetServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vet")
public class VetController {
    private final VetServicesImpl vetServices;

    public VetController(VetServicesImpl vetServices) {
        this.vetServices = vetServices;
    }

    @PostMapping("")
    public HttpStatus addVet(@RequestBody VetDto vet) {
        boolean empty = vetServices.addVet(vet );
        if (empty) {
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
