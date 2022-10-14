import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.*;

import frc.robot.InterpolatingMap;

public class InterpolationTest {
    
@Test
public void testEmptyMap() {
    InterpolatingMap map = new InterpolatingMap(Map.of());
    double speed = map.getSpeedFromDistance(5);
    assertEquals(0, speed, 0.00000001);
}

@Test
public void testExactDistance() {
    InterpolatingMap map = new InterpolatingMap(Map.of(5.0,1.0));
    double speedExact = map.getSpeedFromDistance(5);
    assertEquals(1, speedExact, 0.00000001);

    double speedLargerThanMax = map.getSpeedFromDistance(9);
    assertEquals(1, speedLargerThanMax, 0.00000001);

    double speedSmallerThanMin = map.getSpeedFromDistance(1);
    assertEquals(1, speedSmallerThanMin, 0.00000001);
}

@Test
public void testInterpolation() {
    InterpolatingMap map = new InterpolatingMap(Map.of(
        5.0, 1.0,
        10.0, 2.0
    ));

    double speedOneFourth = map.getSpeedFromDistance(6.25);
    assertEquals(1.25, speedOneFourth, 0.00000001);

    double speedHalf = map.getSpeedFromDistance(7.5);
    assertEquals(1.5, speedHalf, 0.00000001);

    double speedThreeFourths = map.getSpeedFromDistance(8.75);
    assertEquals(1.75, speedThreeFourths, 0.00000001);
}

}


