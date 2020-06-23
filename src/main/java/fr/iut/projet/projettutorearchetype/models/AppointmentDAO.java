package fr.iut.projet.projettutorearchetype.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDAO {
    private int startTime;
    private Integer userid;
    private Integer offerid;
}
