package speed.wagon.mspapp.service.impl;

import org.springframework.stereotype.Service;
import speed.wagon.mspapp.dao.AirplaneCharacteristicsRepository;
import speed.wagon.mspapp.model.AirplaneCharacteristics;
import speed.wagon.mspapp.service.AirplaneCharacteristicsService;

@Service
public class AirplaneCharacteristicsServiceImpl implements AirplaneCharacteristicsService {
    private final AirplaneCharacteristicsRepository airplaneCharacteristicsRepository;

    public AirplaneCharacteristicsServiceImpl(AirplaneCharacteristicsRepository airplaneCharacteristicsRepository) {
        this.airplaneCharacteristicsRepository = airplaneCharacteristicsRepository;
    }

    @Override
    public AirplaneCharacteristics save(AirplaneCharacteristics airplaneCharacteristics) {
        return airplaneCharacteristicsRepository.save(airplaneCharacteristics);
    }
}
