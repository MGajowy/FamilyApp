package pl.gajewski.FamilyApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateFamilyResponse {
    private String numberFamily;
    private String information;
}
