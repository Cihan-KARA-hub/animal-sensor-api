package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.services.impl.MedicalHistoryImp;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:50140")
@RestController
@RequestMapping("api/v1/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryImp medicalHistoryServices;

    public MedicalHistoryController(MedicalHistoryImp medicalHistoryServices) {
        this.medicalHistoryServices = medicalHistoryServices;
    }

    @GetMapping("/{idTag}")
    public List<MedicalHistoryDto> getMedicalHistory(@PathVariable String idTag) {
        List<MedicalHistoryDto> dto = medicalHistoryServices.getMedicalHistory(idTag);
        if (dto.isEmpty()) {
            return null;
        }
        return dto;
    }

    @DeleteMapping("{id}")
    public HttpStatusCode deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryServices.deleteMedicalHistory(id);
        return HttpStatusCode.valueOf(200);
    }
}
