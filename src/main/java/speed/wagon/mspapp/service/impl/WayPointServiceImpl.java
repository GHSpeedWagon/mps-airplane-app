package speed.wagon.mspapp.service.impl;

import org.springframework.stereotype.Service;
import speed.wagon.mspapp.dao.WayPointRepository;
import speed.wagon.mspapp.model.WayPoint;
import speed.wagon.mspapp.service.WayPointService;

@Service
public class WayPointServiceImpl implements WayPointService {
    private final WayPointRepository wayPointRepository;

    public WayPointServiceImpl(WayPointRepository wayPointRepository) {
        this.wayPointRepository = wayPointRepository;
    }

    @Override
    public WayPoint save(WayPoint wayPoint) {
        return wayPointRepository.save(wayPoint);
    }
}
