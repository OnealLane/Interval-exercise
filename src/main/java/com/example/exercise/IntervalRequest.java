package com.example.exercise;

import lombok.Data;

import java.util.List;

@Data
public class IntervalRequest {

    private List <Interval> includes;
    private List <Interval> excludes;

    public IntervalRequest(List<Interval> includes, List<Interval> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

}
