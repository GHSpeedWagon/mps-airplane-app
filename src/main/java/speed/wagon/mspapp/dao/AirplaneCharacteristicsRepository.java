package speed.wagon.mspapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import speed.wagon.mspapp.model.AirplaneCharacteristics;

@Repository
public interface AirplaneCharacteristicsRepository extends MongoRepository<AirplaneCharacteristics, String> {
}
