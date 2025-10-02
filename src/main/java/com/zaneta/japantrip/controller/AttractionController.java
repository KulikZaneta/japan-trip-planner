package com.zaneta.japantrip.controller;

import com.zaneta.japantrip.mapper.attraction.AttractionMapper;
import com.zaneta.japantrip.model.Attraction;
import com.zaneta.japantrip.model.dto.attraction.AttractionRequest;
import com.zaneta.japantrip.model.dto.attraction.AttractionResponse;
import com.zaneta.japantrip.service.AttractionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    private final AttractionMapper attractionMapper;

    public AttractionController(AttractionService attractionService, AttractionMapper attractionMapper) {
        this.attractionService = attractionService;
        this.attractionMapper = attractionMapper;
    }

    @GetMapping
    public ResponseEntity<List<AttractionResponse>> getAllAttractions() {
        List<Attraction> attractionList = attractionService.getAttractionList();
        List<AttractionResponse> attractionResponseList = attractionMapper.toAttractionResponseList(attractionList);

        return ResponseEntity.ok(attractionResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionResponse> getAttractionById(@PathVariable Long id) {
        Attraction attractionById = attractionService.getAttractionById(id);
        AttractionResponse attractionResponse = attractionMapper.toAttractionResponse(attractionById);

        return ResponseEntity.ok(attractionResponse);
    }

    @PostMapping
    public ResponseEntity<AttractionResponse> createAttraction(@RequestBody @Valid AttractionRequest request) {
        Attraction attraction = attractionMapper.toAttraction(request);
        Attraction savedAttraction = attractionService.createAttraction(attraction, request.getCityName());
        AttractionResponse attractionResponse = attractionMapper.toAttractionResponse(savedAttraction);

        return ResponseEntity.status(HttpStatus.CREATED).body(attractionResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttractionResponse> updateAttraction(@PathVariable Long id, @RequestBody @Valid AttractionRequest request) {
        Attraction attraction = attractionMapper.toAttraction(request);
        attraction.setId(id);

        Attraction updateAttraction = attractionService.updateAttraction(attraction);
        AttractionResponse attractionResponse = attractionMapper.toAttractionResponse(updateAttraction);

        return ResponseEntity.ok(attractionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttractionById(@PathVariable Long id) {
        attractionService.deleteAttractionById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

