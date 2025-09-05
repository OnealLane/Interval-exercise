package com.example.exercise;


import com.example.exercise.service.IntervalService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntervalServiceTest {

  private final IntervalService intervalService = new IntervalService();


    @Test
    void mergeTest() {
        Interval includes = new Interval(0, 15);
        Interval includes1 = new Interval(10, 25);
        Interval includes2 = new Interval(30, 56);

        List<Interval> includeIntervals = Arrays.asList(includes, includes1, includes2);

        List<Interval> result = intervalService.mergeIntervals(includeIntervals);

        assertEquals(2, result.size());
        assertEquals(new Interval(0, 25), result.get(0));
        assertEquals(new Interval(30, 56), result.get(1));

    }
}
