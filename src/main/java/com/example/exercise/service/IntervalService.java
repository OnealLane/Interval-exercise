package com.example.exercise.service;

import com.example.exercise.Interval;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class IntervalService {

    public List<Interval> mergeAndExcludeIntervals(List<Interval> include, List<Interval> exclude) {
        List<Interval> mergedIntervals = mergeIntervals(include);
        return subtractExcludes(mergedIntervals, exclude);
    }



    public List<Interval> mergeIntervals(List <Interval> intervals) {
        if (intervals.isEmpty()) {
            return new ArrayList<>();
        }

        intervals.sort(Comparator.comparingInt(Interval :: getStart));
        List<Interval> mergedIntervals = new ArrayList <>();

        Interval current = intervals.get(0);

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


    public List<Interval> subtractExcludes(List<Interval> includes, List<Interval> excludes) {
        if (includes.isEmpty()) {
            return includes;
        }
        List<Interval> result = new ArrayList <>(includes);

        for (Interval exclude : excludes) {
            result = result.stream()
                    .flatMap(currentInclude -> subtract(currentInclude, exclude)
                            .stream())
                            .toList();
        }

        return result;
    }


    public List<Interval> subtract(Interval include, Interval exclude) {
        List<Interval> result = new ArrayList <>();

        if(exclude.getEnd() < include.getStart() || exclude.getStart() > include.getEnd()) {
            result.add(include);

        } else {

            if (exclude.getStart() > include.getStart()) {
                int leftEnd = exclude.getStart() - 1;
                if(include.getStart() <= leftEnd) {
                    result.add(new Interval(include.getStart(), exclude.getStart() - 1));
                }

            } if (exclude.getEnd() < include.getEnd()) {
                int rightStart = exclude.getEnd() + 1;
                if (include.getEnd() >= rightStart) {
                    result.add(new Interval(exclude.getEnd() + 1, include.getEnd()));
                }
            }
        }

        return result;
    }
}
