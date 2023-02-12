package speed.wagon.mspapp.service.impl;

import org.springframework.stereotype.Component;
import speed.wagon.mspapp.dao.AirplaneRepository;
import speed.wagon.mspapp.model.Airplane;
import speed.wagon.mspapp.model.AirplaneCharacteristics;
import speed.wagon.mspapp.model.Flight;
import speed.wagon.mspapp.model.TemporaryPoint;
import speed.wagon.mspapp.model.WayPoint;
import speed.wagon.mspapp.service.AirplaneService;
import speed.wagon.mspapp.service.FlightService;
import speed.wagon.mspapp.service.PlaneCalculation;
import speed.wagon.mspapp.service.TemporaryPointService;
import speed.wagon.mspapp.service.WayPointService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PlaneCalculationImpl implements PlaneCalculation {
    private final FlightService flightService;
    private final TemporaryPointService temporaryPointService;
    private final AirplaneService airplaneService;

    public PlaneCalculationImpl(FlightService flightService,
                                TemporaryPointService temporaryPointService,
                                AirplaneService airplaneService) {
        this.flightService = flightService;
        this.temporaryPointService = temporaryPointService;
        this.airplaneService = airplaneService;
    }

    @Override
    public List<TemporaryPoint> calculateRoute(AirplaneCharacteristics characteristics, List<WayPoint> wayPoints) {
        List<TemporaryPoint> temporaryPoints = new ArrayList<>();

        TemporaryPoint temporaryPoint = getDefaultPosition();
        temporaryPoint = temporaryPointService.save(temporaryPoint);
        Airplane airplane = new Airplane();
        airplane.setAirplaneCharacteristics(characteristics);
        airplane.setPosition(temporaryPoint);
        airplane = airplaneService.save(airplane);
        temporaryPoints.add(temporaryPoint);
        for (WayPoint point : wayPoints) {
            Double distance = findDistanceBetweenTwoPoints(airplane.getPosition().getLatitude(),
                    airplane.getPosition().getLongitude(), point.getLatitude(),
                    point.getLongitude());
            System.out.println("distance between way points=" + distance);
            Double time = findTime(distance,
                    airplane.getAirplaneCharacteristics().getMaxSpeed(),
                    airplane.getAirplaneCharacteristics().getMaxAcceleration());
            System.out.println("time in minutes to overcame=" + String.format("%.2f", time));
            TemporaryPoint currentPos = new TemporaryPoint();
            currentPos.setLongitude(point.getLongitude());
            currentPos.setLatitude(point.getLatitude());
            currentPos.setFlightSpeed(point.getFlightSpeed());
            currentPos.setFlightHeight(point.getFlightHeight());
            Double course = findCourse(airplane.getPosition(), point);
            currentPos.setCourse(course);
            currentPos = temporaryPointService.save(currentPos);
            airplane.setPosition(currentPos);
            airplaneService.save(airplane);
            temporaryPoints.add(currentPos);
        }
        Flight flight = new Flight();
        flight.setWayPoints(wayPoints);
        airplane.setFlights(List.of(flight));
        flight.setTemporaryPoints(temporaryPoints);
        flightService.save(flight);
        airplaneService.save(airplane);
        System.out.println(flight);
        return temporaryPoints;
    }

    private Double findCourse(TemporaryPoint currentPos, WayPoint nextPoint) {
        Double course = 0.0;
        if (Objects.equals(currentPos.getLatitude(), nextPoint.getLatitude())) {
            if (Objects.equals(currentPos.getLongitude(), nextPoint.getLongitude())) {
                return 0.0;
            } else if (currentPos.getLongitude() < nextPoint.getLongitude()) {
                return 90.0;
            } else {
                return 270.0;
            }
        } else if (currentPos.getLatitude() < nextPoint.getLatitude()) {
            if (Objects.equals(currentPos.getLongitude(), nextPoint.getLongitude())) {
                return 0.0;
            } else if (currentPos.getLongitude() < nextPoint.getLongitude()) {
                return 45.0;
            } else if (currentPos.getLongitude() > nextPoint.getLongitude()) {
                return 315.0;
            }
        } else if (currentPos.getLatitude() > nextPoint.getLatitude()) {
            if (Objects.equals(currentPos.getLongitude(), nextPoint.getLongitude())) {
                return 180.0;
            } else if (currentPos.getLongitude() < nextPoint.getLongitude()) {
                return 135.0;
            } else if (currentPos.getLongitude() > nextPoint.getLongitude()) {
                return 225.0;
            }
        }
        return course;
    }

    private Double findDistanceBetweenTwoPoints(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
        Double distance = Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2))
                * 6371;
        return distance;
    }

    public Double findTime(Double globalDistance, Double maxSpeed, Double acceleration) {
        Double timeToMaxSpeed = (maxSpeed - 0) / acceleration;
        Double distance = 0 * timeToMaxSpeed + (0.5) * acceleration * Math.pow(timeToMaxSpeed, 2);
        if (globalDistance < distance * 2) {
            timeToMaxSpeed = Math.pow(2 * globalDistance / (0 + maxSpeed),0.5) * 60;
            return timeToMaxSpeed;
        }
        Double timeToZeroSpeed = timeToMaxSpeed;
        Double timeOnMaxSpeed = (globalDistance - (distance * 2)) / maxSpeed;
        return timeOnMaxSpeed + timeToZeroSpeed + timeOnMaxSpeed;
    }

    private TemporaryPoint getDefaultPosition() {
        TemporaryPoint startPosition = new TemporaryPoint();
        startPosition.setLatitude(swapToRadians(50.423278)); //0.88005236...
        startPosition.setLongitude(swapToRadians(30.083722)); //0.525060...
        startPosition.setFlightHeight(0.0);
        startPosition.setFlightSpeed(0.0);
        startPosition.setCourse(0.0);
        return startPosition;
    }

    private Double swapToRadians(Double decimalCoordinates) {
        //from decimal coordinates to radians
        return decimalCoordinates / 57.29577951308;
    }
}
