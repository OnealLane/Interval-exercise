package com.example.exercise.service;

import com.example.exercise.Interval;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service class for handling interval logic
 *
 * Could argue some methods could be private, I made them public
 * so it would be easier to run tests.
 */
@Service
public class IntervalService {

    /**
     * Merges the include intervals and subtracts the exclude intervals
     *
     * @param include
     * @param exclude
     * @return list of non overlapping intervals
     */
    public List<Interval> mergeAndExcludeIntervals(List<Interval> include, List<Interval> exclude) {
        List<Interval> mergedIntervals = mergeIntervals(include);
        return subtractExcludes(mergedIntervals, exclude);
    }


    /**
     * Merges include intervals
     *
     * @param intervals
     * @return a list of merged include intervals
     */
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

    /**
     * Subtracts a list of exclude intervals from a list of include intervals
     *
     * @param includes
     * @param excludes
     * @return a list of intervals
     */
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

    /**
     * Helper function
     * Subtracts a single exclude interval from a single include interval
     *
     * @param include
     * @param exclude
     * @return a list of intervals after subtracting
     */
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
