package com.example.exercise;

import lombok.Data;

@Data
public class Interval {

    private int start;
    private int end;

    public Interval (int start, int end) {
        this.start = start;
        this.end = end;
    }
}
