package speed.wagon.mspapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import speed.wagon.mspapp.model.WayPoint;

@Repository
public interface WayPointRepository extends MongoRepository<WayPoint, String> {
}
