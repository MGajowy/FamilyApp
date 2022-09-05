package pl.gajewski.FamilyApp.controller;

import org.springframework.web.bind.annotation.*;
import pl.gajewski.FamilyApp.controller.constant.FamilyAppConstant;
import pl.gajewski.FamilyApp.dto.CreateFamilyResponse;
import pl.gajewski.FamilyApp.dto.FamilyResponse;
import pl.gajewski.FamilyApp.dto.FamilyVO;
import pl.gajewski.FamilyApp.service.FamilyAppService;


@RestController
public class FamilyAppController {

    private final FamilyAppService familyAppService;

    public FamilyAppController(FamilyAppService familyAppService) {
        this.familyAppService = familyAppService;
    }

    @PostMapping(FamilyAppConstant.CREATE_FAMILY)
    public CreateFamilyResponse createFamily(@RequestBody FamilyVO request) {
        return familyAppService.createFamily(request);
    }

    @GetMapping(FamilyAppConstant.GET_FAMILY + "/{id}")
    public FamilyResponse getFamily(@PathVariable(value = "id") Long id) {
        return familyAppService.getFamily(id);
    }
}
