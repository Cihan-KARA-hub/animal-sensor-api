package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.AnimalHealthDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.services.impl.AnimalHealthRuntimeImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:50140")
@RestController
@RequestMapping("api/v1/animal-health")
public class AnimalHealthRuntimeController {
    private final AnimalHealthRuntimeImpl animalHealthRuntimeServices;


    public AnimalHealthRuntimeController(AnimalHealthRuntimeImpl animalHealthRuntimeServices) {
        this.animalHealthRuntimeServices = animalHealthRuntimeServices;

    }
    @GetMapping("get-sensor/{idTag}")
    public ResponseEntity<AnimalHealthDto> getAnimalHealthDto(@PathVariable String idTag,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        AnimalHealthDto dto = animalHealthRuntimeServices.getAnimals(idTag, pageable);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/post-sensor")
    public HttpStatus createAnimalHealthDto(@RequestBody SensorDto dto) throws IOException {
        boolean a = animalHealthRuntimeServices.addAnimalHealthHourlyRuntime(dto);
        if (a) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
