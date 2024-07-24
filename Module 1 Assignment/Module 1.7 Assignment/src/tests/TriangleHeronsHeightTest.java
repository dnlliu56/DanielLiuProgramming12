import com.liu.Colour;
import com.liu.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleHeronsHeightTest {

    /**
     * Partitioned the inputs as follows:
     * side1 = side2 = side3
     * side1 != side2, side2 = side3
     * side1 != side2 != side3
     *
     * side1 = 0, side2 positive, side3, positive
     * side1 = side2 + side3, side1 != 0
     */

    @Test
    public void testHeronsEquilateralTriangle() {
        Triangle triangle = new Triangle(3, 3, 3, Colour.NONE);
        double expectedCalculatedHeight = 2.598;
        assertEquals(expectedCalculatedHeight, triangle.heronsHeight(), 0.01);
    }

    @Test
    public void testHeronsIsoscelesTriangle() {
        Triangle triangle = new Triangle(3, 4, 4, Colour.NONE);
        double expectedCalculatedHeight = 2.781;
        assertEquals(expectedCalculatedHeight, triangle.heronsHeight(), 0.01);
    }

    @Test
    public void testHeronsScaleneTriangle() {
        Triangle triangle = new Triangle(3, 4, 5, Colour.NONE);
        double expectedCalculatedHeight = 3;
        assertEquals(expectedCalculatedHeight, triangle.heronsHeight(), 0.01);
    }

    @Test
    public void testHeronsBoundaries() {
        Triangle triangle = new Triangle(0, 3, 3, Colour.NONE);
        double expectedCalculatedHeight = 0;
        assertEquals(expectedCalculatedHeight, triangle.heronsHeight(), 0.01);
    }

    @Test
    public void testHeronsBoundariesDegenerateTriangle() {
        Triangle triangle = new Triangle(5, 2, 3, Colour.NONE);
        double expectedCalculatedHeight = 0;
        assertEquals(expectedCalculatedHeight, triangle.heronsHeight(), 0.01);
    }
}