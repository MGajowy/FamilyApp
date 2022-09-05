package pl.gajewski.FamilyApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMember {
    private String familyName;
    private String givenName;
    private Integer age;
    private Long familyId;
}
