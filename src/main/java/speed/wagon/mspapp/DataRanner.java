package speed.wagon.mspapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import speed.wagon.mspapp.model.AirplaneCharacteristics;
import speed.wagon.mspapp.model.WayPoint;
import speed.wagon.mspapp.service.PlaneCalculation;
import speed.wagon.mspapp.service.WayPointService;

import java.util.List;

@SpringBootApplication
public class DataRanner implements CommandLineRunner {
    private WayPointService wayPointService;
    private DataInitializer dataInitializer;
    private PlaneCalculation planeCalculation;
    private List<WayPoint> wayPoints;

    public DataRanner(WayPointService wayPointService, DataInitializer dataInitializer,
                      PlaneCalculation planeCalculation) {
        this.wayPointService = wayPointService;
        this.dataInitializer = dataInitializer;
        this.planeCalculation = planeCalculation;
    }

    @Override
    public void run(String[] args) throws Exception {

        AirplaneCharacteristics firstAirplaneCharacteristics = new AirplaneCharacteristics();
        firstAirplaneCharacteristics.setMaxSpeed(94.0);
        firstAirplaneCharacteristics.setMaxAcceleration(16.0);
        firstAirplaneCharacteristics.setChangingHeightSpeed(9.0);
        firstAirplaneCharacteristics.setChangingCourseSpeed(6.0);

        dataInitializer.setSpeed(firstAirplaneCharacteristics.getMaxSpeed() / 1.4);
        wayPoints = dataInitializer.getWayPoints();
        wayPoints.stream().forEach(wayPointService::save);
        System.out.println("\n" + "Firs airplane:");
        System.out.println("Characteristics:" + firstAirplaneCharacteristics);
        planeCalculation.calculateRoute(firstAirplaneCharacteristics, wayPoints);

        AirplaneCharacteristics secondAirplaneCharacteristics = new AirplaneCharacteristics();
        secondAirplaneCharacteristics.setMaxSpeed(222.0);
        secondAirplaneCharacteristics.setMaxAcceleration(20.0);
        secondAirplaneCharacteristics.setChangingHeightSpeed(13.0);
        secondAirplaneCharacteristics.setChangingCourseSpeed(12.0);
        System.out.println("\n" + "Second airplane:");
        System.out.println("Characteristics:" + firstAirplaneCharacteristics);
        planeCalculation.calculateRoute(secondAirplaneCharacteristics, wayPoints);

        AirplaneCharacteristics thirdAirplaneCharacteristics = new AirplaneCharacteristics();
        thirdAirplaneCharacteristics.setMaxSpeed(44.0);
        thirdAirplaneCharacteristics.setMaxAcceleration(1.0);
        thirdAirplaneCharacteristics.setChangingHeightSpeed(17.0);
        thirdAirplaneCharacteristics.setChangingCourseSpeed(17.0);
        System.out.println("\n" + "Third airplane:");
        System.out.println("Characteristics:" + firstAirplaneCharacteristics);
        planeCalculation.calculateRoute(thirdAirplaneCharacteristics, wayPoints);
    }
}
