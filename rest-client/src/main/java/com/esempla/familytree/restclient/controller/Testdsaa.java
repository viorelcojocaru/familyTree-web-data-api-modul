package com.esempla.familytree.restclient.controller;

import com.esempla.familytree.restclient.dto.PersonDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Testdsaa {
    public static void main(String[] args) throws IOException {
      /*  JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        JSONObject perosn1 = new JSONObject();
        JSONObject perosn2 = new JSONObject();
        perosn1.put("id", 1);
        perosn1.put("firstName", "Viorel");
        perosn1.put("lastName", "Cojocaru");
        perosn2.put("id", 2);
        perosn2.put("firstName", "Dragos");
        perosn2.put("lastName", "Cojocaru");
        ja.add(perosn1);
        ja.add(perosn2);
        jo.put("personList", ja);
        JSONArray ja2 =(JSONArray) jo.get("personList");
        System.out.println(ja2);
        for (Object json :ja2  ) {
            System.out.println((JSONObject)json);
        }

        List<Person> personDtoList=new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        List<Person> personList = mapper.readValue(ja2.toJSONString(), new TypeReference<List<Person>>(){});
        System.out.println(personList);

*/
//                JSONArray ja = new JSONArray();
//                ja.addAll(mapList);
//                JSONObject object = new JSONObject();
//                object.put("personList", ja);

//                ObjectMapper mapper = new ObjectMapper();
//                PersonDto personDto =  {
//                });


        mimi();



    }
//    @Data
//    static class Person{
//        long id;
//        String firstName;
//        String lastName;
//    }

   static void mimi(){
        String simpleJson="{\n\t\"firstName\" : \"Rodica\",\n\t\"lastName\" : \"Zgureanu\", \n\t\"gender\" : 20,\n\t\"birthDate\" : \"1985-09-16T21:00:00Z\",\n\t\"inALive\" : true \n}";
//        String simpleJson="{\"firstName\" : \"Rodica\",\"lastName\" : \"Zgureanu\", \"gender\" : 20,\"birthDate\" : \"1985-09-16T21:00:00Z\",\"inALive\" : true }";

//        simpleJson=simpleJson.replaceAll("\n","");
//        simpleJson=simpleJson.replaceAll("\t","");
       System.out.println(simpleJson);



       ObjectMapper objectMapper = new ObjectMapper();
       Map<String, Object> jsonMap = null;
       try {
           jsonMap = objectMapper.readValue(simpleJson,
                   new TypeReference<Map<String,Object>>(){});
       } catch (IOException e) {
           e.printStackTrace();
       }
       JSONObject object = new JSONObject(jsonMap);
       System.out.println(object);
    }


}
