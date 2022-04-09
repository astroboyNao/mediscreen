package com.abernathy.assess.controller;

import com.abernathy.assess.model.dto.AssessDTO;
import com.abernathy.assess.service.AssessService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AssessController {

    AssessService assessService;

    @GetMapping(value = "/assess/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessDTO getAssess(@PathVariable("patientId") Long patientId) {
        return assessService.get(patientId);
    }

}
