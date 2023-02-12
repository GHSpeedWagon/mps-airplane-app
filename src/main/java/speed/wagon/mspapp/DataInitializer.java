package speed.wagon.mspapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import speed.wagon.mspapp.model.WayPoint;

import java.util.List;

@Component
@ComponentScan("application")
public class DataInitializer {
    private static WayPoint firstWayPoint;
    private static WayPoint secondWayPoint;
    private static WayPoint thirdWayPoint;

    public void setSpeed(Double speed) {
        firstWayPoint = new WayPoint();
        firstWayPoint.setLatitude(swapToRadians(50.431139));
        firstWayPoint.setLongitude(swapToRadians(30.128222));
        firstWayPoint.setFlightHeight(15.0);
        firstWayPoint.setFlightSpeed(speed);

        secondWayPoint = new WayPoint();
        secondWayPoint.setLatitude(swapToRadians(50.393472));
        secondWayPoint.setLongitude(swapToRadians(30.144667));
        secondWayPoint.setFlightHeight(15.0);
        secondWayPoint.setFlightSpeed(speed);

        thirdWayPoint = new WayPoint();
        thirdWayPoint.setLatitude(swapToRadians(50.425972));
        thirdWayPoint.setLongitude(swapToRadians(30.200750));
        thirdWayPoint.setFlightHeight(15.0);
        thirdWayPoint.setFlightSpeed(speed);
    }
    public List<WayPoint> getWayPoints() {
        return List.of(firstWayPoint, secondWayPoint, thirdWayPoint);
    }

    private static Double swapToRadians(Double decimalCoordinates) {
        return decimalCoordinates / 57.29577951308;
    }
}
