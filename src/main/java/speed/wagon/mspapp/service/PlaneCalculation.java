package speed.wagon.mspapp.service;

import speed.wagon.mspapp.model.AirplaneCharacteristics;
import speed.wagon.mspapp.model.TemporaryPoint;
import speed.wagon.mspapp.model.WayPoint;
import java.util.List;

public interface PlaneCalculation {
    List<TemporaryPoint> calculateRoute(AirplaneCharacteristics characteristics, List<WayPoint> wayPoints);
}
