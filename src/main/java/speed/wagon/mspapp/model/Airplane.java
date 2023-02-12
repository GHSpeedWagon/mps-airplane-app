package speed.wagon.mspapp.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
@Data
public class Airplane {
    @Id
    private String id;
    private AirplaneCharacteristics airplaneCharacteristics;
    private TemporaryPoint position;
    private List<Flight> flights;
}
