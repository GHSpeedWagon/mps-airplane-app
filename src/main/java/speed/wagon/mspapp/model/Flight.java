package speed.wagon.mspapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
@Data
public class Flight {
    @Id
    private String number;

    private List<WayPoint> wayPoints;

    private List<TemporaryPoint> temporaryPoints;
}