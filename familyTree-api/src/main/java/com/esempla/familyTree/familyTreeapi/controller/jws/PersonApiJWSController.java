package com.esempla.familyTree.familyTreeapi.controller.jws;

import com.esempla.familyTree.familyTreeapi.controller.PersonRestController;
import com.esempla.familyTree.familyTreeapi.controller.jws.utils.JWSUtil;
import com.esempla.familyTree.familyTreedata.domain.PersonDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/jws/person")
@Slf4j
@RequiredArgsConstructor
@Api(value = "Person Api JWS Controller", description = "Show person rest-api with certification")
public class PersonApiJWSController {

    private final PersonRestController personRestController;

    public final JWSUtil jwsUtil;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
//    @ApiOperation(value = "no values asked",
//            httpMethod = "Get",
//            protocols = "JWS certificate is required",
//            code = 200,
//            response = String.class,
//            tags = "list , persons, json, server")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                        name = "JSONObject"
                        ,value = "encoded String SSH 256 JSONObject"
                        , dataType = "String"
                    )

            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success response"),
                    @ApiResponse(code = 401, message = "Not found") } )
    public String getList() {
        String signedJSON = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<PersonDto> personList = personRestController.list();
            List<Map<String, Object>> mapList = mapper.convertValue(personList, new TypeReference<List<Map<String, Object>>>() {
            });
            JSONArray ja = new JSONArray();
            ja.addAll(mapList);
            JSONObject object = new JSONObject();
            object.put("personList", ja);
            signedJSON = jwsUtil.signServer(object);
            System.err.println(object);
            System.err.println(signedJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signedJSON;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "body"
                            ,value = "Value that must be saved in data base of json of person"
                            , dataType = "String"

                    )

            }
    )
    @ApiImplicitParam(
         value = "{\"id\":null,\"firstName\":\"Viorel\",\"lastName\":\"Cojocaru\",\"lastNameOnBirth\":Cojocaru,\"gender\":10,\"birthDate\":443653200000,\"inALive\":true,\"deathDate\":null,\"photoPath\":https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjC3czKj8DeAhUC2KQKHUh2B8YQjRx6BAgBEAU&url=https%3A%2F%2Flifehacker.com%2Fdiscover-where-your-photos-are-illegally-being-used-onl-1825282627&psig=AOvVaw3_m4xg2qozr5dLeIxjbmRH&ust=1541605873806749}"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success response"),
                    @ApiResponse(code = 401, message = "Not found") } )
    public String create(@RequestBody String encodedJson) throws ParseException {
        String signedJSON = null;
        JSONObject jsonObject = jwsUtil.serializeJWSObjectServer(encodedJson);
        ObjectMapper mapper = new ObjectMapper();
        PersonDto personDto = null;
        try {
            personDto = mapper.readValue(jsonObject.toJSONString(), new TypeReference<PersonDto>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        PersonDto personCreated = personRestController.create(personDto);

        Map<String, Object> map = mapper.convertValue(personCreated, new TypeReference<Map<String, Object>>() {
        });
        JSONObject object = new JSONObject(map);
        try {
            signedJSON = jwsUtil.signServer(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return signedJSON;
    }
}
