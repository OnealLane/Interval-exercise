package com.example.exercise.controller;


import com.example.exercise.Interval;
import com.example.exercise.IntervalRequest;
import com.example.exercise.service.IntervalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * REST controller for interval operations.
 * Provides endpoints to merge and exclude intervals.
 */
@RestController
@RequestMapping("/api/intervals")
public class IntervalController {

    private final IntervalService intervalService;

    public IntervalController(IntervalService intervalService) {
        this.intervalService = intervalService;
    }


    @PostMapping("/calculate")
    public ResponseEntity<List <Interval>> mergeAndExcludeIntervals (@RequestBody IntervalRequest request) {
        List<Interval> result = intervalService.mergeAndExcludeIntervals(request.getIncludes(), request.getExcludes());
        return ResponseEntity.ok(result);
    }
}
