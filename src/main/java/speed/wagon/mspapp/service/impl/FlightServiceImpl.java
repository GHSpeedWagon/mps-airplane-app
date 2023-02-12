package speed.wagon.mspapp.service.impl;

import org.springframework.stereotype.Service;
import speed.wagon.mspapp.dao.FlightRepository;
import speed.wagon.mspapp.model.Flight;
import speed.wagon.mspapp.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

}
