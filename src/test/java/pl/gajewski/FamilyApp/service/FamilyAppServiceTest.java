package pl.gajewski.FamilyApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pl.gajewski.FamilyApp.controller.constant.FamilyAppConstant;
import pl.gajewski.FamilyApp.dto.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FamilyAppServiceTest {
    private static final String URI_CREATE_FAMILY = "http://familyapp-family-database-1:8022/db/createFamily";
    private static final String URI_CREATE_FAMILY_MEMBER = "http://familyapp-family-member-app-1:8021/createFamilyMember";
    private static final String URI_SEARCH_FAMILY_OF_ID = "http://familyapp-family-database-1:8022/db/searchFamily/1";
    private static final String URI_SEARCH_FAMILY_MEMBER_OF_ID = "http://familyapp-family-member-app-1:8021/searchFamilyMember/1";

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    FamilyAppService familyAppService;

    @Test
    void shouldCreateFamily() {
        //when
        when(restTemplate.postForObject(Mockito.eq(URI_CREATE_FAMILY), Mockito.any(Family.class), Mockito.eq(Long.class))).thenReturn(1L);
        CreateFamilyResponse actual = familyAppService.createFamily(FamilyVO.builder()
                .id(1L)
                .familyMemberList(createFamilyMember())
                .nrOfinfants(0)
                .nrOfChildren(0)
                .nrOfAdults(1)
                .familyName("NOWAK")
                .build());
        // then
        assertThat(actual.getInformation()).isEqualTo(FamilyAppConstant.PASS_INFORMATION);
        assertThat(actual.getNumberFamily()).isEqualTo(1L);
    }

    @Test
    void shouldNotCreateFamily() {
        //when
        CreateFamilyResponse actual = familyAppService.createFamily(FamilyVO.builder()
                .familyMemberList(createFamilyMember())
                .nrOfinfants(1)
                .nrOfChildren(0)
                .nrOfAdults(1)
                .familyName("NOWAK")
                .build());
        // then
        assertThat(actual.getInformation()).isEqualTo(FamilyAppConstant.FAULT_INFORMATION);
        assertThat(actual.getNumberFamily()).isNull();
    }

    @Test
    void shouldGetFamily() {
        // given
        when(restTemplate.getForObject(Mockito.eq(URI_SEARCH_FAMILY_OF_ID), Mockito.eq(Family.class))).thenReturn(createFamily());
        when(restTemplate.getForObject(Mockito.eq(URI_SEARCH_FAMILY_MEMBER_OF_ID), Mockito.eq(FamilyMemberResponse[].class))).thenReturn(createFamilyMemberTab());
        // when
        FamilyResponse actual = familyAppService.getFamily(1L);
        // then
        assertThat(actual.getFamilyName()).isEqualTo("NOWAK");
        assertThat(actual.getFamilyMemberList().get(0).getGivenName()).isEqualTo("Karolina");
        assertThat(actual.getFamilyMemberList().get(0).getAge()).isEqualTo(28);
        assertThat(actual.getNrOfAdults()).isEqualTo(1);
    }

    private List<FamilyMember> createFamilyMember() {
        List<FamilyMember> familyMemberList = new ArrayList<>();
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyId(1L);
        familyMember.setFamilyName("NOWAK");
        familyMember.setAge(30);
        familyMember.setGivenName("Adam");
        familyMemberList.add(familyMember);
        return familyMemberList;
    }

    private FamilyMemberResponse[] createFamilyMemberTab() {
        List<FamilyMemberResponse> familyMemberResponses = new ArrayList<>();
        FamilyMemberResponse response = new FamilyMemberResponse();
        response.setFamilyName("NOWAK");
        response.setGivenName("Karolina");
        response.setAge(28);
        familyMemberResponses.add(response);
        return familyMemberResponses.toArray(new FamilyMemberResponse[0]);
    }

    private Family createFamily() {
        Family family = new Family();
        family.setUuidFamily("111");
        family.setFamilyName("NOWAK");
        family.setNrOfinfants(0);
        family.setNrOfChildren(0);
        family.setNrOfAdults(1);
        family.setId(1L);
        return family;
    }
}