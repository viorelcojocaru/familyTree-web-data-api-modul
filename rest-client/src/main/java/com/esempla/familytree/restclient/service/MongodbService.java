package com.esempla.familytree.restclient.service;

import com.esempla.familytree.restclient.dto.PersonDto;
import com.esempla.familytree.restclient.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongodbService {
    private final MongoTemplate mongoTemplate;
    private final PersonRepository repository;

    public void savePerson(String object) {
        DBObject dbObject = (DBObject) com.mongodb.util.JSON.parse(object);
        mongoTemplate.insert(dbObject, "person");

    }

    public JSONObject savePerson(PersonDto personDto){
        return toJSONConverter(repository.save(personDto));
    }

    public JSONArray list() {
        return toJSONArrayConverter(repository.findAll());
    }

    public JSONObject getById(long id) {
        return toJSONConverter(repository.findById(id).get());
    }


    public void delete(long id) {
        repository.deleteById(id);
    }

    public JSONArray getByFirstName(String firstName) {
        return toJSONArrayConverter(repository.findPersonDtoByFirstName(firstName));
    }

    private JSONArray toJSONArrayConverter(List<PersonDto> personlist) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(personlist);
        return jsonArray;
    }

    private JSONObject toJSONConverter(PersonDto person) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.convertValue(person, new TypeReference<Map<String, Object>>() {
        });
        JSONObject object = new JSONObject(map);
        return object;
    }

}
