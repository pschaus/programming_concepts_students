package parallelization;

import org.javagrader.Allow;
import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;

@Grade
@Allow("java.lang.Thread")
public class DiskCatalogTest {
    private class MyDisk implements DiskCatalog.Disk {
        private String bandName;
        private String diskTitle;
        private int year;

        MyDisk(String bandName,
               String diskTitle,
               int year) {
            this.bandName = bandName;
            this.diskTitle = diskTitle;
            this.year = year;
        }

        @Override
        public String getBandName() {
            return bandName;
        }

        @Override
        public String getDiskTitle() {
            return diskTitle;
        }

        @Override
        public int getYear() {
            return year;
        }
    }

    private List<DiskCatalog.Disk> getTestCollection() {
        List<DiskCatalog.Disk> c = new LinkedList<DiskCatalog.Disk>();
        c.add(new MyDisk("Pink Floyd", "The Dark Side of the Moon", 1973));
        c.add(new MyDisk("Oasis", "Heathen Chemistry", 2002));
        c.add(new MyDisk("Guns N' Roses",  "Appetite for Destruction", 1987));
        c.add(new MyDisk("Imagine Dragons", "Night Visions", 2012));
        c.add(new MyDisk("Oasis", "Definitely Maybe", 1994));
        c.add(new MyDisk("Oasis", "Be Here Now", 1997));
        c.add(new MyDisk("Imagine Dragons", "Origins", 2018));
        c.add(new MyDisk("Imagine Dragons", "Evolve", 2017));
        c.add(new MyDisk("Nirvana", "Nevermind", 1991));
        c.add(new MyDisk("Radiohead", "OK Computer", 1997));
        c.add(new MyDisk("The Beatles", "Abbey Road", 1969));
        c.add(new MyDisk("The Smiths", "The Queen is Dead", 1986));
        c.add(new MyDisk("The Doors", "The Doors", 1967));
        c.add(new MyDisk("Queen", "A Night at the Opera", 1975));
        c.add(new MyDisk("Boston", "Boston", 1976));
        c.add(new MyDisk("The Beatles", "Let It Be", 1970));
        c.add(new MyDisk("The Replacements", "Let It Be", 1984));
        c.add(new MyDisk("The Cure", "Faith", 1981));
        c.add(new MyDisk("George Michael", "Faith", 1987));
        c.add(new MyDisk("U2", "Pop", 1997));
        c.add(new MyDisk("GAS", "Pop", 2000));
        c.add(new MyDisk("Badfinger", "Wish You Were Here", 1974));
        c.add(new MyDisk("Pink Floyd", "Wish You Were Here", 1975));
        return c;
    }

    private int execute(List<DiskCatalog.Disk> disks,
                        Optional<String> bandName,
                        Optional<String> diskTitle,
                        Optional<Integer> year,
                        int skip) {
        return new DiskCatalog.CountMatchingDisksCallable(disks.iterator(), bandName, diskTitle, year, skip).call();        
    }
    
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testSequential() {
        List<DiskCatalog.Disk> c = getTestCollection();
        for (int i = 0; i < 10; i++) {
            assertEquals(2, execute(c, Optional.of("The Beatles"), Optional.empty(), Optional.empty(), 0));
            assertEquals(3, execute(c, Optional.of("Oasis"), Optional.empty(), Optional.empty(), 0));
            assertEquals(2, execute(c, Optional.empty(), Optional.of("Faith"), Optional.empty(), 0));
            assertEquals(2, execute(c, Optional.empty(), Optional.of("Wish You Were Here"), Optional.empty(), 0));
            assertEquals(3, execute(c, Optional.empty(), Optional.empty(), Optional.of(1997), 0));
            assertEquals(1, execute(c, Optional.empty(), Optional.empty(), Optional.of(2000), 0));

            assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.of("Wish You Were Here"), Optional.empty(), 0));
            assertEquals(1, execute(c, Optional.of("Oasis"), Optional.empty(), Optional.of(1997), 0));
            assertEquals(1, execute(c, Optional.empty(), Optional.of("Pop"), Optional.of(1997), 0));
            assertEquals(1, execute(c, Optional.of("Oasis"), Optional.of("Be Here Now"), Optional.of(1997), 0));

            assertEquals(0, execute(c, Optional.of("Nope"), Optional.of("Be Here Now"), Optional.of(1997), 0));
            assertEquals(0, execute(c, Optional.of("Oasis"), Optional.of("Nope"), Optional.of(1997), 0));
            assertEquals(0, execute(c, Optional.of("Oasis"), Optional.of("Be Here Now"), Optional.of(2000), 0));

            assertEquals(23, execute(c, Optional.empty(), Optional.empty(), Optional.empty(), 0));  // Matches any album

            Collections.shuffle(c);
        }
    }
    
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testSkip() {
        // Pink Floyd has albums #0 and #22
        List<DiskCatalog.Disk> c = getTestCollection();
        assertEquals(2, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 0));
        assertEquals(2, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 1));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 2));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 3));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 4));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 5));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 6));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 7));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 8));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 9));
        assertEquals(2, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 10));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 11));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 12));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 13));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 14));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 15));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 16));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 17));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 18));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 19));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 20));
        assertEquals(2, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 21));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 22));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 23));
        assertEquals(1, execute(c, Optional.of("Pink Floyd"), Optional.empty(), Optional.empty(), 24));
    }
    
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testOneThread() throws ExecutionException, InterruptedException {
        List<DiskCatalog.Disk> c = getTestCollection();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
            
        try {
            for (int i = 0; i < 10; i++) {
                assertEquals(2, DiskCatalog.countMatchingDisks(c, Optional.of("The Beatles"), Optional.empty(), Optional.empty(), threadPool, 1));
                assertEquals(3, DiskCatalog.countMatchingDisks(c, Optional.of("Oasis"), Optional.empty(), Optional.empty(), threadPool, 1));
                assertEquals(2, DiskCatalog.countMatchingDisks(c, Optional.empty(), Optional.of("Faith"), Optional.empty(), threadPool, 1));
                assertEquals(2, DiskCatalog.countMatchingDisks(c, Optional.empty(), Optional.of("Wish You Were Here"), Optional.empty(), threadPool, 1));
                assertEquals(3, DiskCatalog.countMatchingDisks(c, Optional.empty(), Optional.empty(), Optional.of(1997), threadPool, 1));
                assertEquals(1, DiskCatalog.countMatchingDisks(c, Optional.empty(), Optional.empty(), Optional.of(2000), threadPool, 1));

                assertEquals(1, DiskCatalog.countMatchingDisks(c, Optional.of("Pink Floyd"), Optional.of("Wish You Were Here"), Optional.empty(), threadPool, 1));
                assertEquals(1, DiskCatalog.countMatchingDisks(c, Optional.of("Oasis"), Optional.empty(), Optional.of(1997), threadPool, 1));
                assertEquals(1, DiskCatalog.countMatchingDisks(c, Optional.empty(), Optional.of("Pop"), Optional.of(1997), threadPool, 1));
                assertEquals(1, DiskCatalog.countMatchingDisks(c, Optional.of("Oasis"), Optional.of("Be Here Now"), Optional.of(1997), threadPool, 1));

                assertEquals(0, DiskCatalog.countMatchingDisks(c, Optional.of("Nope"), Optional.of("Be Here Now"), Optional.of(1997), threadPool, 1));
                assertEquals(0, DiskCatalog.countMatchingDisks(c, Optional.of("Oasis"), Optional.of("Nope"), Optional.of(1997), threadPool, 1));
                assertEquals(0, DiskCatalog.countMatchingDisks(c, Optional.of("Oasis"), Optional.of("Be Here Now"), Optional.of(2000), threadPool, 1));

                assertEquals(23, DiskCatalog.countMatchingDisks(c, Optional.empty(), Optional.empty(), Optional.empty(), threadPool, 1));  // Matches any album
                Collections.shuffle(c);
            }
        } finally {
            threadPool.shutdown();
        }
    }

}
