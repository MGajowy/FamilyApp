package pl.gajewski.FamilyApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateFamilyResponse {
    private Long numberFamily;
    private String information;
}
