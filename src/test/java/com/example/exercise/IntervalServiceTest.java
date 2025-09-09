package com.example.exercise;


import com.example.exercise.service.IntervalService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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

    @Test
    void removeTest() {
        Interval includes = new Interval(10, 100);
        Interval excludes = new Interval(40, 60);

        List<Interval> result = intervalService.subtract(includes, excludes);

        List<Interval> expectedResult = Arrays.asList(
                new Interval(10, 39),
                new Interval(61, 100)
        );

        assertEquals(expectedResult, result);
    }

    @Test
    void subtractTest() {
        List<Interval> includes = Arrays.asList(
                new Interval(10, 100),
                new Interval(200, 300)
        );

        List<Interval> excludes = Arrays.asList(
                new Interval(20, 30),
                new Interval(95, 200)
        );

        List<Interval> result = intervalService.mergeAndExcludeIntervals(includes, excludes);

        List<Interval> expectedResult = Arrays.asList(
                new Interval(10, 19),
                new Interval(31, 94),
                new Interval(201, 300)
        );

        assertEquals(expectedResult, result);
    }

    @Test
    void emptyIncludesTest() {
        List<Interval> includes = Arrays.asList();
        List<Interval> excludes = List.of(new Interval(10, 20));

        List <Interval> result = intervalService.mergeAndExcludeIntervals(includes, excludes);

        assertEquals(0, result.size());
    }

    @Test
    void emptyExcludesTest() {
        List<Interval> excludes = Arrays.asList();
        List<Interval> includes = Arrays.asList(new Interval(10, 20));

        List <Interval> result = intervalService.mergeAndExcludeIntervals(includes, excludes);

        assertEquals(List.of(new Interval(10, 20)), result);
    }

    @Test
    void fullyOverlappingTest() {
        List<Interval> includes = Arrays.asList(new Interval(10, 50));
        List<Interval> excludes = Arrays.asList(new Interval(5, 60));

        List<Interval> result = intervalService.mergeAndExcludeIntervals(includes, excludes);

        assertEquals(0, result.size());
    }

    @Test
    void partiallyOverlappingTest() {
        List<Interval> includes = Arrays.asList(new Interval(10, 50));
        List<Interval> excludes = Arrays.asList(new Interval(40, 60));

        List<Interval> result = intervalService.mergeAndExcludeIntervals(includes, excludes);

        assertEquals(List.of(new Interval(10, 39)), result);
        assertEquals(1, result.size());

    }


}
