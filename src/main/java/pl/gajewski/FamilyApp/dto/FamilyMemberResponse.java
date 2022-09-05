package pl.gajewski.FamilyApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMemberResponse {
    private String familyName;
    private String givenName;
    private Integer age;
}
