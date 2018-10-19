package com.esempla.familyTree.familyTreewebstarter.web;

import com.esempla.familyTree.familyTreedata.domain.Contact;
import com.esempla.familyTree.familyTreedata.service.ContactService;
import com.esempla.familyTree.familyTreedata.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ContactController {

    private final CountryService countryService;

    private final ContactService contactService;

    @Autowired
    private PersonController personController;

    @PostMapping("/editPersonContactSave")
    public ModelAndView savePerson(@Valid Contact contact, BindingResult result) {
        contactService.saveOrUpdate(contact);
        return personController.getPersonById(contact.getPerson().getId(), new ModelAndView());
    }

}
