package speed.wagon.mspapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import speed.wagon.mspapp.model.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
}
