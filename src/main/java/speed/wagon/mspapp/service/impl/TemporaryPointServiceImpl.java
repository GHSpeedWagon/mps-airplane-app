package speed.wagon.mspapp.service.impl;

import org.springframework.stereotype.Service;
import speed.wagon.mspapp.dao.TemporaryPointRepository;
import speed.wagon.mspapp.model.TemporaryPoint;
import speed.wagon.mspapp.service.TemporaryPointService;

@Service
public class TemporaryPointServiceImpl implements TemporaryPointService {
    private final TemporaryPointRepository temporaryPointRepository;

    public TemporaryPointServiceImpl(TemporaryPointRepository temporaryPointRepository) {
        this.temporaryPointRepository = temporaryPointRepository;
    }

    @Override
    public TemporaryPoint save(TemporaryPoint temporaryPoint) {
        return temporaryPointRepository.save(temporaryPoint);
    }
}
