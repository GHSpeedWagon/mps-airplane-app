package speed.wagon.mspapp.service.impl;

import org.springframework.stereotype.Service;
import speed.wagon.mspapp.dao.AirplaneRepository;
import speed.wagon.mspapp.model.Airplane;
import speed.wagon.mspapp.service.AirplaneService;


@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }
}
