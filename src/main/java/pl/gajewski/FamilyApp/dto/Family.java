package pl.gajewski.FamilyApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family {
    private Long id;
    private String familyName;
    private Integer nrOfAdults;
    private Integer nrOfChildren;
    private Integer nrOfinfants;
    private String uuidFamily;
}

