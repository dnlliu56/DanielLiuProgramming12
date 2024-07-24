import com.liu.Triangle;
import com.liu.Colour;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleTest {

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
    public void testEquilateralTriangle() {
        Triangle triangle = new Triangle(3, 3, 3, Colour.NONE);
        double expectedCalculatedArea = 3.897;
        assertEquals(expectedCalculatedArea, triangle.getArea(), 0.01);
    }

    @Test
    public void testIsoscelesTriangle() {
        Triangle triangle = new Triangle(3, 4, 4, Colour.NONE);
        double expectedCalculatedArea = 5.567;
        assertEquals(expectedCalculatedArea, triangle.getArea(), 0.01);
    }

    @Test
    public void testScaleneTriangle() {
        Triangle triangle = new Triangle(3, 4, 5, Colour.NONE);
        double expectedCalculatedArea = 6;
        assertEquals(expectedCalculatedArea, triangle.getArea(), 0.01);
    }

    @Test
    public void testBoundaries() {
        Triangle triangle = new Triangle(0, 3, 3, Colour.NONE);
        double expectedCalculatedArea = 0;
        assertEquals(expectedCalculatedArea, triangle.getArea(), 0.01);
    }

    @Test
    public void testBoundariesDegenerateTriangle() {
        Triangle triangle = new Triangle(5, 2, 3, Colour.NONE);
        double expectedCalculatedArea = 0;
        assertEquals(expectedCalculatedArea, triangle.getArea(), 0.01);
    }
}