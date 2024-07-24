import com.liu.Circle;
import com.liu.Colour;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CircleTest {

    /**
     * Partitioned the inputs as follows:
     * radius -> positive
     *
     * radius = 0
     */

    @Test
    public void testCircle() {
        Circle circle = new Circle(3, Colour.NONE);
        double expectedCalculatedArea = 28.274;
        assertEquals(expectedCalculatedArea, circle.getArea(), 0.01);
    }

    @Test
    public void testBoundaries() {
        Circle circle = new Circle(0, Colour.NONE);
        double expectedCalculatedArea = 0;
        assertEquals(expectedCalculatedArea, circle.getArea(), 0.01);
    }
}