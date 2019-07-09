package com.esempla.familytree.restclient.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(value = "Person Controller API", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {


    private final PersonClient client;
    private final MongodbService mongodbService;
    private final JWSUtil jwsUtil;

    @GetMapping("/findAllPerson")
    @ApiOperation(value = "Gets all person from dataBase", notes = "Some notes abut this method for example t return all person from data base")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PersonDto.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    public JSONObject getAllPersonJson() {
        String encodedStr = null;
        JSONObject signedJSON = null;
        try {
            encodedStr = client.getPersons();
            signedJSON = jwsUtil.serializeJWSObjectClient(encodedStr);

        } catch (Exception e) {

        } finally {
            if (signedJSON != null) {
                List<PersonDto> personDtoList = new ArrayList<>();
                JSONArray ja = (JSONArray) signedJSON.get("personList");
                ObjectMapper mapper = new ObjectMapper();
                List<PersonDto> personList = new ArrayList<>();
                try {
                    personList = mapper.readValue(ja.toJSONString(), new TypeReference<List<PersonDto>>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (PersonDto personDto : personList) {
                    mongodbService.savePerson(personDto);
                }
            }
        }
        return signedJSON;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createPersonJson")
    @ApiOperation(value = "Create object person and save to dataBase", notes = "Info for method Post mothro that help you to save you data in data base")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = PersonDto.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "contents",
                    dataType = "CustomTypeFor2031",
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(value = "{'property': 'test'}", mediaType = "application/json")
                            }))
    })
    //@ApiModelProperty(name = "ApiModelProperty PersonDto",notes = "ApiModelProperty notes customizat", value = "ApiModelProperty value customizat",example = "ApiModelProperty example customizat",dataType = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject create(@ApiParam(value = "ApiParam create", required = true) @RequestBody String simpleJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = mapper.readValue(simpleJson, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject(jsonMap);

        String signedJSON = null;
        try {
            signedJSON = jwsUtil.signClient(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (signedJSON == null) {
            throw new Exception("is noll singedJson from encoding");
        }
        String recivedSignedJSON = client.savePersonOnServer(signedJSON);
        JSONObject receivedJSONobject = jwsUtil.serializeJWSObjectClient(recivedSignedJSON);

        PersonDto personDto = mapper.convertValue(receivedJSONobject, new TypeReference<PersonDto>() {
        });
        JSONObject receivedJSONobjectCreated = mongodbService.savePerson(personDto);
        return receivedJSONobjectCreated;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/updatePersonJson")
    public JSONObject update(@RequestBody String simpleJson) throws Exception {
        return create(simpleJson);
    }


}
