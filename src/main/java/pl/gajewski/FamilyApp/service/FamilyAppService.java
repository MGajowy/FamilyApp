package pl.gajewski.FamilyApp.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.gajewski.FamilyApp.controller.constant.FamilyAppConstant;
import pl.gajewski.FamilyApp.dto.*;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class FamilyAppService {
    @Value("${service.uriCreateFamily}")
    private String uriCreateFamily;

    @Value("${service.uriCreateFamilyMember}")
    private String uriCreateFamilyMember;

    @Value("${service.uriSearchFamilyOfId}")
    private String uriSearchFamilyOfId;

    @Value("${service.uriSearchFamilyMemberOfId}")
    private String uriSearchFamilyMemberOfId;

    private final RestTemplate restTemplate;

    @Autowired
    public FamilyAppService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CreateFamilyResponse createFamily(FamilyVO request) {
        Boolean result = validateFamilyData(request);
        if (Boolean.TRUE.equals(result)) {
            UUID uuid = UUID.randomUUID();
            String uuidFamily = uuid.toString();
            Family family = new Family();
            BeanUtils.copyProperties(request, family);
            family.setUuidFamily(uuidFamily);
            Long familyID = restTemplate.postForObject(uriCreateFamily, family, Long.class);
            for (FamilyMember familyMember : request.getFamilyMemberList()) {
                familyMember.setFamilyId(familyID);
                restTemplate.postForObject(uriCreateFamilyMember, familyMember, FamilyMember.class);
            }
            return CreateFamilyResponse.builder()
                    .numberFamily(familyID.toString())
                    .information(FamilyAppConstant.PASS_INFORMATION)
                    .build();
        }
        return CreateFamilyResponse.builder()
                .numberFamily(null)
                .information(FamilyAppConstant.FAULT_INFORMATION)
                .build();
    }

    private Boolean validateFamilyData(FamilyVO request) {
        Integer adults = 0;
        Integer childerns = 0;
        Integer inflants = 0;
        for (FamilyMember a : request.getFamilyMemberList()) {
            if (a.getAge() >= 0 && a.getAge() <= 4) {
                inflants++;
            } else if (a.getAge() > 4 && a.getAge() <= 16) {
                childerns++;
            } else if (a.getAge() > 16) {
                adults++;
            }
        }
        return checkFamily(request, adults, childerns, inflants);
    }

    private Boolean checkFamily(FamilyVO request, Integer adults, Integer childerns, Integer inflants) {
        if (request.getNrOfAdults().equals(adults) &&
                request.getNrOfChildren().equals(childerns) &&
                request.getNrOfinfants().equals(inflants)) {
            log.info(FamilyAppConstant.PASS_INFORMATION + request.getFamilyName());
            return true;
        } else {
            log.warn("Błąd walidacji: " + FamilyAppConstant.FAULT_INFORMATION);
            return false;
        }
    }

    public FamilyResponse getFamily(Long id) {
        Family responsFamily = restTemplate.getForObject(uriSearchFamilyOfId + id.toString(), Family.class);
        FamilyMemberResponse[] listResponseFamily = restTemplate.getForObject(uriSearchFamilyMemberOfId + id.toString(), FamilyMemberResponse[].class);
        return FamilyResponse.builder()
                .familyName(responsFamily.getFamilyName())
                .nrOfAdults(responsFamily.getNrOfAdults())
                .nrOfChildren(responsFamily.getNrOfChildren())
                .nrOfinfants(responsFamily.getNrOfinfants())
                .familyMemberList(List.of(listResponseFamily))
                .build();
    }
}
