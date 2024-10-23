package basics;

import java.util.*;

import java.util.Random;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Grade
public class MazoutTest {

    private static final Random random = new Random(134203);

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void toyExample() {
        Mazout.City city = new Mazout.City();
        Mazout.House h1 = city.addHouse(12);
        Mazout.House h2 = city.addHouse(6);
        Mazout.House h3 = city.addHouse(15);
        Mazout.House h4 = city.addHouse(30);
        Mazout.House h5 = city.addHouse(21);
        Mazout.House h6 = city.addHouse(39);
        Mazout.House h7 = city.addHouse(55);
        Mazout.House h8 = city.addHouse(27);
        Mazout.House h9 = city.addHouse(30);

        h1.setNeighborRight(h2);
        h1.setNeighborDown(h3);

        h2.setNeighborLeft(h1);
        h2.setNeighborRight(h3);
        h2.setNeighborDown(h5);

        h3.setNeighborLeft(h2);
        h3.setNeighborDown(h6);

        h4.setNeighborRight(h5);
        h4.setNeighborAbove(h1);
        h4.setNeighborDown(h7);

        h5.setNeighborLeft(h4);
        h5.setNeighborRight(h6);
        h5.setNeighborAbove(h2);
        h5.setNeighborDown(h8);

        h6.setNeighborLeft(h5);
        h6.setNeighborAbove(h3);
        h6.setNeighborDown(h9);

        h7.setNeighborRight(h8);
        h7.setNeighborAbove(h4);

        h8.setNeighborLeft(h9);
        h8.setNeighborRight(h7);
        h8.setNeighborAbove(h5);

        h9.setNeighborLeft(h8);
        h9.setNeighborAbove(h6);

        int demand = Mazout.getTotalDemand(city, h1, new String[]{"right", "down", "left", "down"});
        assertEquals(12+6+21+30+55, demand);

        assertEquals(30, Mazout.getTotalDemand(city, h9, new String[]{}));
    }

    /**
     * Generates a rectangular grid of houses.
     */
    private static class RectangularGrid {
        private final Mazout.City city;
        private final Mazout.House[][] houses;
        private final int rows;
        private final int cols;

        public RectangularGrid(int rows,
                               int cols) {
            city = new Mazout.City();
            houses = new Mazout.House[rows][cols];
            this.rows = rows;
            this.cols = cols;

            for (int row = 0; row < rows; row++) {
                for (int col=0; col < cols; col++) {
                    houses[row][col] = city.addHouse(10 + random.nextInt(90));
                }
            }
            
            for (int row = 0; row < rows; row++) {
                for (int col=0; col < cols; col++) {
                    if (row > 0) {
                        houses[row][col].setNeighborAbove(houses[row-1][col]);
                    }
                    if (row < rows - 1) {
                        houses[row][col].setNeighborDown(houses[row+1][col]);
                    }
                    if (col > 0) {
                        houses[row][col].setNeighborLeft(houses[row][col-1]);
                    }
                    if (col < cols - 1) {
                        houses[row][col].setNeighborRight(houses[row][col+1]);
                    }
                }
            }
        }

        public Mazout.House getHouse(int row, int col) {
            return houses[row][col];
        }

        public Mazout.City getCity() {
            return city;
        }

        public int getRows() {
            return rows;
        }

        public int getCols() {
            return cols;
        }
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testVisitMultipleTimes2() {
        RectangularGrid grid = new RectangularGrid(3, 3);

        int demand = Mazout.getTotalDemand(grid.getCity(), grid.getHouse(1, 1), new String[]{});
        assertEquals(grid.getHouse(1,1).getCapacity(), demand);

        demand = Mazout.getTotalDemand(grid.getCity(), grid.getHouse(1, 1), new String[]{"right"});
        assertEquals(grid.getHouse(1,1).getCapacity() + grid.getHouse(1,2).getCapacity(), demand);

        demand = Mazout.getTotalDemand(grid.getCity(), grid.getHouse(1, 1),
                                       new String[]{"right", "left", "right", "left", "right", "left"});
        assertEquals(grid.getHouse(1,1).getCapacity() + grid.getHouse(1,2).getCapacity(), demand);
    }

}
