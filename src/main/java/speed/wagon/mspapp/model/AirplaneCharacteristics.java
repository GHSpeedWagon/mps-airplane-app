package speed.wagon.mspapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class AirplaneCharacteristics {
    @Id
    private String id;
    private Double maxSpeed;
    private Double maxAcceleration;
    private Double changingHeightSpeed;
    private Double changingCourseSpeed;
}
