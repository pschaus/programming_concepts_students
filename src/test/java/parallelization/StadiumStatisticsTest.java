package parallelization;

import org.javagrader.Allow;
import org.javagrader.Grade;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Grade
@Allow("java.lang.Thread")
public class StadiumStatisticsTest {
    private static class MySupporter implements StadiumStatistics.Supporter {
        private String nationality;
        private int age;

        public MySupporter(String nationality,
                           int age) {
            this.nationality = nationality;
            this.age = age;
        }

        @Override
        public String getNationality() {
            return nationality;
        }

        @Override
        public int getAge() {
            return age;
        }
    }

    private void checkHistogram(StadiumStatistics.AgeHistogram histogram,
                                int between_0_and_9,
                                int between_10_and_19,
                                int between_20_and_29,
                                int between_30_and_39,
                                int between_40_and_49,
                                int between_50_and_59,
                                int between_60_and_69,
                                int between_70_and_79,
                                int above_80) {
        assertEquals(between_0_and_9, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_0_AND_9));
        assertEquals(between_10_and_19, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_10_AND_19));
        assertEquals(between_20_and_29, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_20_AND_29));
        assertEquals(between_30_and_39, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_30_AND_39));
        assertEquals(between_40_and_49, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_40_AND_49));
        assertEquals(between_50_and_59, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_50_AND_59));
        assertEquals(between_60_and_69, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_60_AND_69));
        assertEquals(between_70_and_79, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.BETWEEN_70_AND_79));
        assertEquals(above_80, histogram.getNumberOfSupporters(StadiumStatistics.AgeCategory.ABOVE_80));
    }

    @Test
    @Grade(value = 2, cpuTimeout = 1)
    public void testHistogram() {
        StadiumStatistics.AgeHistogram h = new StadiumStatistics.AgeHistogram();
        checkHistogram(h, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        for (int i = 0; i < 120; i++) {
            h.addSupporter(i);
            checkHistogram(h,
                    Math.min(10, i + 1),
                    Math.max(0, Math.min(20, i + 1) - 10),
                    Math.max(0, Math.min(30, i + 1) - 20),
                    Math.max(0, Math.min(40, i + 1) - 30),
                    Math.max(0, Math.min(50, i + 1) - 40),
                    Math.max(0, Math.min(60, i + 1) - 50),
                    Math.max(0, Math.min(70, i + 1) - 60),
                    Math.max(0, Math.min(80, i + 1) - 70),
                    Math.max(0, i + 1 - 80));
        }
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testHistogramException() {
        StadiumStatistics.AgeHistogram h = new StadiumStatistics.AgeHistogram();
        assertThrows(IllegalArgumentException.class, () -> h.addSupporter(-1));
    }

    @Test
    @Grade(value = 2, cpuTimeout = 1)
    public void testCombine() {
        StadiumStatistics.AgeHistogram h1 = new StadiumStatistics.AgeHistogram();
        StadiumStatistics.AgeHistogram h2 = new StadiumStatistics.AgeHistogram();
        for (int i = 0; i < 100; i++) {
            h1.addSupporter(i);
            h2.addSupporter(i);
        }
        checkHistogram(StadiumStatistics.AgeHistogram.combine(h1, h2), 20, 20, 20, 20, 20, 20, 20, 20, 40);
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void getEmpty() {
        StadiumStatistics.Supporter supporters[] = new StadiumStatistics.Supporter[0];
        StadiumStatistics.AgeHistogram h = StadiumStatistics.computeAgeHistogramSequential(supporters, "", 0, 0);
        checkHistogram(h, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testBasicSequential1() {
        StadiumStatistics.Supporter supporters[] = new StadiumStatistics.Supporter[100];
        for (int i = 0; i < supporters.length; i++) {
            supporters[i] = new MySupporter("Belgian", i);
        }

        StadiumStatistics.AgeHistogram h = StadiumStatistics.computeAgeHistogramSequential(supporters, "French", 0, supporters.length);
        checkHistogram(h, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        h = StadiumStatistics.computeAgeHistogramSequential(supporters, "Belgian", 0, supporters.length);
        checkHistogram(h, 10, 10, 10, 10, 10, 10, 10, 10, 20);
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testBasicSequential2() {
        StadiumStatistics.Supporter supporters[] = new StadiumStatistics.Supporter[100];
        for (int i = 0; i < supporters.length / 2; i++) {
            supporters[i] = new MySupporter("French", i);
        }
        for (int i = supporters.length / 2; i < supporters.length; i++) {
            supporters[i] = new MySupporter("Belgian", i);
        }

        StadiumStatistics.AgeHistogram h = StadiumStatistics.computeAgeHistogramSequential(supporters, "French", 0, supporters.length);
        checkHistogram(h, 10, 10, 10, 10, 10, 0, 0, 0, 0);

        h = StadiumStatistics.computeAgeHistogramSequential(supporters, "Belgian", 0, supporters.length);
        checkHistogram(h, 0, 0, 0, 0, 0, 10, 10, 10, 20);
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testBasicParallel1() {
        StadiumStatistics.Supporter supporters[] = new StadiumStatistics.Supporter[100];
        for (int i = 0; i < supporters.length; i++) {
            supporters[i] = new MySupporter("Belgian", i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2 /* numberOfThreads */);

        try {
            StadiumStatistics.AgeHistogram h = StadiumStatistics.computeAgeHistogramParallel(executorService, supporters, "French");
            checkHistogram(h, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            h = StadiumStatistics.computeAgeHistogramParallel(executorService, supporters, "Belgian");
            checkHistogram(h, 10, 10, 10, 10, 10, 10, 10, 10, 20);
        } finally {
            executorService.shutdown();
        }
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testBasicParallel2() {
        StadiumStatistics.Supporter supporters[] = new StadiumStatistics.Supporter[100];
        for (int i = 0; i < supporters.length / 2; i++) {
            supporters[i] = new MySupporter("French", i);
        }
        for (int i = supporters.length / 2; i < supporters.length; i++) {
            supporters[i] = new MySupporter("Belgian", i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2 /* numberOfThreads */);

        try {
            StadiumStatistics.AgeHistogram h = StadiumStatistics.computeAgeHistogramParallel(executorService, supporters, "French");
            checkHistogram(h, 10, 10, 10, 10, 10, 0, 0, 0, 0);

            h = StadiumStatistics.computeAgeHistogramParallel(executorService, supporters, "Belgian");
            checkHistogram(h, 0, 0, 0, 0, 0, 10, 10, 10, 20);
        } finally {
            executorService.shutdown();
        }
    }

}
