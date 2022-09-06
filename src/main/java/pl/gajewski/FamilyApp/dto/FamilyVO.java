package pl.gajewski.FamilyApp.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyVO {
    private Long id;
    private String familyName;
    private Integer nrOfAdults;
    private Integer nrOfChildren;
    private Integer nrOfinfants;
    private List<FamilyMember> familyMemberList;
}
