package com.example.exercise.service;

import com.example.exercise.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalService {



    public List<Interval> mergeIntervals(List <Interval> intervals) {

        intervals.sort(Comparator.comparingInt(Interval :: getStart));
        List<Interval> mergedIntervals = new ArrayList <Interval>();

        Interval current = intervals.getFirst();

        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            if(current.getEnd() >= next.getStart()) {
                current.setEnd(Math.max(current.getEnd(), next.getEnd()));
            } else {
                mergedIntervals.add(current);
                current = next;
            }
        }
        mergedIntervals.add(current);
        return mergedIntervals;
    }
}
