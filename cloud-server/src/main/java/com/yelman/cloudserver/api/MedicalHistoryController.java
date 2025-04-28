package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.services.MedicalHistoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:65017")
@RestController
@RequestMapping("api/v1/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryServices medicalHistoryServices;

    public MedicalHistoryController(MedicalHistoryServices medicalHistoryServices) {
        this.medicalHistoryServices = medicalHistoryServices;
    }

    @GetMapping("/{id}")
    public HttpStatus getMedicalHistory(@PathVariable Long id) {
        List<MedicalHistoryDto> dto = medicalHistoryServices.getMedicalHistory(id);
        if (dto.isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }
}
