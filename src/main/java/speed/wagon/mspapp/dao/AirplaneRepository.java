package speed.wagon.mspapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import speed.wagon.mspapp.model.Airplane;

@Repository
public interface AirplaneRepository extends MongoRepository<Airplane, String> {
}
