package speed.wagon.mspapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class TemporaryPoint {
    @Id
    private String id;
    private Double latitude;
    private Double longitude;
    private Double flightHeight;
    private Double flightSpeed;
    private Double course;
}