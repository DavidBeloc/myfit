package com.davdev.http.rest;

import com.davdev.dto.NutritionCreateEditDto;
import com.davdev.dto.NutritionReadDto;
import com.davdev.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/users/{userId}/nutrition")
@RequiredArgsConstructor
public class NutritionController {

    private final NutritionService nutritionService;

    @GetMapping("/{id}")
    public NutritionReadDto findById(@PathVariable("id") Long id) {
        return nutritionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/date/{nutritionDate}")
    public NutritionReadDto findByUserIdAndDate(@PathVariable("userId") Long userId,
                                                @PathVariable("nutritionDate") LocalDate date) {
        return nutritionService.findByUserIdAndDate(userId, date)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NutritionReadDto create(@RequestBody NutritionCreateEditDto nutritionDto) {
        return nutritionService.create(nutritionDto);
    }

    @PutMapping("/{id}")
    public NutritionReadDto update(@PathVariable("id") Long id,
                                   @RequestBody NutritionCreateEditDto nutritionDto) {
        return nutritionService.update(id, nutritionDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return nutritionService.delete(id) ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}