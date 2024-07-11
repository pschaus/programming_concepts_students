package parallelization;

import org.javagrader.Allow;
import org.javagrader.Grade;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@Grade
@Allow("java.lang.Thread")
class TrainStationsTest {
    // https://en.wikipedia.org/wiki/Haversine_formula
    private static class HaversineDistance implements TrainStations.DistanceFunction {
        private static double square(double a) {
            return a * a;
        }

        @Override
        public double compute(TrainStations.Location a,
                              TrainStations.Location b) {
            double R = 6371.0;  // Mean radius of Earth in kilometers: https://en.wikipedia.org/wiki/Earth_radius
            double phi1 = a.getLatitude() / 180.0 * Math.PI;
            double phi2 = b.getLatitude() / 180.0 * Math.PI;
            double dlambda = (b.getLongitude() - a.getLongitude()) / 180.0 * Math.PI;
            return 2.0 * R * Math.asin(
                    Math.sqrt(square(Math.sin((phi2 - phi1) / 2.0)) +
                            Math.cos(phi1) * Math.cos(phi2) * square(Math.sin(dlambda / 2.0))));
        }
    }

    private static class MyStation implements TrainStations.Station {
        private String name;
        private TrainStations.Location location;

        MyStation(String name,
                  double latitude,
                  double longitude) {
            this.name = name;
            this.location = new TrainStations.Location(latitude, longitude);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public TrainStations.Location getLocation() {
            return location;
        }
    }

    private static final MyStation STATION_GUILLEMINS = new MyStation("Guillemins", 50.622935, 5.568461);
    private static final MyStation STATION_OTTIGNIES = new MyStation("Ottignies", 50.671632, 4.569615);
    private static final MyStation STATION_NAMUR = new MyStation("Namur", 50.469156, 4.862057);
    private static final MyStation STATION_LLN = new MyStation("Louvain-la-Neuve", 50.6696947, 4.6134822);
    private static final MyStation STATION_BRUXELLES_NORD = new MyStation("Bruxelles Nord", 50.859658, 4.360854);


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testGetClosestStation() {
        Optional<TrainStations.Station> c = TrainStations.getClosestStation(STATION_LLN.getLocation(), new HaversineDistance(),
                Optional.of(STATION_NAMUR), Optional.of(STATION_OTTIGNIES));
        assertNotEquals(c, null);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testFindClosestStationSequential() {
        List<TrainStations.Station> stations = Arrays.asList(new TrainStations.Station[]{STATION_GUILLEMINS, STATION_OTTIGNIES, STATION_NAMUR, STATION_BRUXELLES_NORD});

        for (int i = 0; i < 10; i++) {
            TrainStations.Station[] s = stations.toArray(new TrainStations.Station[0]);
            Optional<TrainStations.Station> c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), s, 0, s.length);
            assertNotEquals(c, null);
            assertTrue(c.isPresent());
            assertEquals("Ottignies", c.get().getName());
            Collections.shuffle(stations);
        }
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testFindClosestStationParallel() {
        List<TrainStations.Station> stations = Arrays.asList(new TrainStations.Station[]{STATION_GUILLEMINS, STATION_OTTIGNIES, STATION_NAMUR, STATION_BRUXELLES_NORD});

        ExecutorService executorService = Executors.newFixedThreadPool(2 /* numberOfThreads */);

        try {
            for (int i = 0; i < 20; i++) {
                TrainStations.Station[] s = stations.toArray(new TrainStations.Station[0]);
                Optional<TrainStations.Station> c = TrainStations.findClosestStationParallel(STATION_LLN.getLocation(), new HaversineDistance(), s, executorService);
                assertNotEquals(c, null);
                assertTrue(c.isPresent());
                assertEquals("Ottignies", c.get().getName());
                Collections.shuffle(stations);
            }
        } finally {
            executorService.shutdown();
        }
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testLimitCasesGetClosestStation() {
        Optional<TrainStations.Station> c = TrainStations.getClosestStation(
                new TrainStations.Location(0, 0), new HaversineDistance(),
                Optional.of(new MyStation("a", 1, 0)), Optional.empty());
        assertNotEquals(c, null);
        assertTrue(c.isPresent());
        assertEquals("a", c.get().getName());

        c = TrainStations.getClosestStation(
                new TrainStations.Location(0, 0), new HaversineDistance(),
                Optional.empty(), Optional.of(new MyStation("b", 2, 0)));
        assertNotEquals(c, null);
        assertTrue(c.isPresent());
        assertEquals("b", c.get().getName());

        c = TrainStations.getClosestStation(
                new TrainStations.Location(0, 0), new HaversineDistance(),
                Optional.empty(), Optional.empty());
        assertNotEquals(c, null);
        assertFalse(c.isPresent());
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testSequentialIndices() {
        TrainStations.Station[] stations = new TrainStations.Station[]{STATION_GUILLEMINS, STATION_OTTIGNIES, STATION_NAMUR, STATION_BRUXELLES_NORD};

        Optional<TrainStations.Station> c;
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 0, 0);
        assertFalse(c.isPresent());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 1, 1);
        assertFalse(c.isPresent());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 2, 2);
        assertFalse(c.isPresent());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 3, 3);
        assertFalse(c.isPresent());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 4, 4);
        assertFalse(c.isPresent());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 0, 1);
        assertTrue(c.isPresent());
        assertEquals("Guillemins", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 1, 2);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 2, 3);
        assertTrue(c.isPresent());
        assertEquals("Namur", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 3, 4);
        assertTrue(c.isPresent());
        assertEquals("Bruxelles Nord", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 0, 2);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 1, 3);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 2, 4);
        assertTrue(c.isPresent());
        assertEquals("Bruxelles Nord", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 0, 3);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 1, 4);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
        c = TrainStations.findClosestStationSequential(STATION_LLN.getLocation(), new HaversineDistance(), stations, 0, 4);
        assertTrue(c.isPresent());
        assertEquals("Ottignies", c.get().getName());
    }


}
