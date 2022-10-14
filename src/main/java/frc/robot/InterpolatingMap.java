package frc.robot;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class InterpolatingMap {
    private TreeMap<Double, Double> map;

    public InterpolatingMap(Map<Double, Double> map) {
        this.map = new TreeMap<>(map);
    }

    public double getSpeedFromDistance(double distance) {
        Double speed = map.get(distance);
        if (speed == null) {
            Entry<Double, Double> c = map.ceilingEntry(distance);
            Entry<Double, Double> f = map.floorEntry(distance);
            if (c == null) {
                if (f == null) {
                    return 0;
                } else {
                    return f.getValue();
                }
            } else {
                if (f == null) {
                    return c.getValue();
                } else {
                    double speedF = f.getValue();
                    double speedC = c.getValue();
                    double distanceF = f.getKey();
                    double distanceC = c.getKey();
                    double percentage = (distance - distanceF) / (distanceC - distanceF);
                    return speedF * (1 - percentage) + speedC * percentage;
                }
            }
        } else {
            return speed.doubleValue();
        }
    }
}
