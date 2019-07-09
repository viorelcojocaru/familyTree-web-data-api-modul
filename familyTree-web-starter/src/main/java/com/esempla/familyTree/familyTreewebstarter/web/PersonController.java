package com.esempla.familyTree.familyTreewebstarter.web;

//import com.esempla.familyTree.familyTreeapi.controller.ResourceNotFoundException;

import com.esempla.familyTree.familyTreecommonutils.constants.AppAccountTypes;
import com.esempla.familyTree.familyTreecommonutils.constants.Genders;
import com.esempla.familyTree.familyTreecommonutils.constants.Pages;
import com.esempla.familyTree.familyTreedata.BC.AccountBC;
import com.esempla.familyTree.familyTreedata.domain.*;
import com.esempla.familyTree.familyTreedata.repository.UserToPersonRepository;
import com.esempla.familyTree.familyTreedata.repository.UserToRoleRepository;
import com.esempla.familyTree.familyTreedata.service.*;
import com.esempla.familyTree.familyTreedata.service.PersonService;
import com.esempla.familyTree.familyTreewebstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final CountryService countryService;
    private final AppAccountService appAccountService;
    private final AppAccountTypeService appAccountTypeService;
    private final AccountBC accountBC;
    private final UserToPersonRepository userToPersonRepository;
    private final UserToRoleRepository userToRoleRepository;
    private final UserService userService;

    @GetMapping("/editPerson/id/{id}")
    public ModelAndView getPersonById(@PathVariable Long id, ModelAndView modelAndView) {
        modelAndView = buildPersonModelAndView(id, modelAndView);
        modelAndView.setViewName("editPerson");
        return modelAndView;
    }

    private ModelAndView buildPersonModelAndView(Long id, ModelAndView modelAndView) {
        Person person;
        if (id == 0)
            person = new Person();
        else {
            if (!personService.existEntry(id)) System.out.println();
            //throw new ResourceNotFoundException("not found person with id:" + id);
            person = personService.getById(id);
        }
        Contact contact = person.getContact();
        if (contact == null) {
            contact = new Contact();
            contact.setPerson(person);
        }
        List<Country> countrys = countryService.listAll();

        modelAndView.addObject("person", person);
        modelAndView.addObject("persons", personService.listAll());
        modelAndView.addObject("contact", contact);
        modelAndView.addObject("countrys", countrys);
        Calendar calendars = Calendar.getInstance();
        modelAndView.addObject("calendars", calendars);
        modelAndView.addObject("source", person);
        return modelAndView;
    }

    @GetMapping("/viewPerson/id/{id}")
    public ModelAndView getViewPersonById(@PathVariable Long id, ModelAndView modelAndView) {
        modelAndView = buildPersonModelAndView(id, modelAndView);
        modelAndView.setViewName("viewPerson");
        return modelAndView;
    }

      @PostMapping(value = "/editPerson/save")
      public ModelAndView save(@Valid Person person, @RequestParam Long sourceId , @RequestParam Long typeId , BindingResult bindingResult, ModelAndView modelAndView) {
          System.out.println(modelAndView.getModel().get("sourceId")); //, Long sourceId
          System.out.println(sourceId); //, Long sourceId
          System.out.println(modelAndView.getModel().get("typeId")) ;//,  Long typeId
          System.out.println(typeId) ;//,  Long typeId
          personService.saveOrUpdate(person);
          System.out.println(person);
          if ( false) {
            AppAccount currentAppAcc = accountBC.createAppAccount(person, appAccountTypeService.getById(typeId));
            appAccountService.saveOrUpdate(currentAppAcc);
            AppAccount appAccountFrom = null;
            AppAccount appAccountTo = null;

            if (person.getGender() == Genders.FEMALE) {
                appAccountFrom = currentAppAcc;
                appAccountTo = appAccountService.getAppAccountBy(sourceId, typeId);
            } else if (person.getGender() == Genders.MALE) {
                appAccountFrom = appAccountService.getAppAccountBy(sourceId, typeId);
                appAccountTo = currentAppAcc;
            }
            try {
                accountBC.createRelation(appAccountFrom, appAccountTo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelAndView.addObject("person", person);
        modelAndView.setViewName("redirect:/viewPerson/id/" + person.getId());
        return modelAndView;
    }

    @PostMapping(value = "/editPerson/id/{id}")
    public ModelAndView savePerson(@Valid Person person, BindingResult bindingResult, ModelAndView modelAndView) {

         personService.saveOrUpdate(person);
//        System.out.println(sourceId);
//        System.out.println(typeId);
        if ( false) {
            AppAccount currentAppAcc = accountBC.createAppAccount(person, appAccountTypeService.getById(typeId));
            appAccountService.saveOrUpdate(currentAppAcc);
            AppAccount appAccountFrom = null;
            AppAccount appAccountTo = null;

            if (person.getGender() == Genders.FEMALE) {
                appAccountFrom = currentAppAcc;
                appAccountTo = appAccountService.getAppAccountBy(sourceId, typeId);
            } else if (person.getGender() == Genders.MALE) {
                appAccountFrom = appAccountService.getAppAccountBy(sourceId, typeId);
                appAccountTo = currentAppAcc;
            }
            try {
                accountBC.createRelation(appAccountFrom, appAccountTo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.typeId);
        System.out.println(this.sourceId);
        if (typeId!=null && sourceId!=null){
            if (this.typeId == AppAccountTypes.CILD)
                System.out.println("CILD");
            if (this.typeId == AppAccountTypes.EX_PARTNER)
                System.out.println("EX_PARTNER");
            if (this.typeId == AppAccountTypes.PARTNER)
                System.out.println("PARTNER");
            if (this.typeId == AppAccountTypes.HUSBAND)
                System.out.println("HUSBAND");
            if (this.typeId == AppAccountTypes.WIFE)
                System.out.println("WIFE");

            this.typeId=null;
            this.sourceId=null;
        }

        modelAndView.addObject("person", person);
        modelAndView.setViewName("redirect:/viewPerson/id/" + person.getId());
        return modelAndView;
    }

    private Long typeId;
    private Long sourceId;

    @GetMapping(value = "/editPerson/id/{id}/typeId/{typeId}/sourceId/{sourceId}")
    public ModelAndView getNewPerson(@PathVariable Long id, @PathVariable Long typeId, @PathVariable Long sourceId, ModelAndView modelAndView) {
        System.out.println(typeId);
        System.out.println(sourceId);
        this.typeId = typeId;
        this.sourceId = sourceId;
//        modelAndView.addObject("typeId", typeId);
//        modelAndView.addObject("sourceId", sourceId);
        modelAndView = buildPersonModelAndView(id, modelAndView);
        modelAndView.setViewName("redirect:/editPerson/id/" + id);//+"/typeId/{typeId}/sourceId/{sourceId}"
        return modelAndView;

    }

//    @PostMapping(value = "/editPerson/id/{id}/typeId/{typeId}/sourceId/{sourceId}")
//    public ModelAndView saveNewPerson(@Valid Person person, @PathVariable Long typeId, @PathVariable Long sourceId, BindingResult bindingResult, ModelAndView modelAndView) {
//        System.out.println(typeId);
//        System.out.println(sourceId);
//
//        modelAndView.addObject("person", person);
//        modelAndView.setViewName("redirect:/viewPerson/id/" + person.getId());
//        return modelAndView;
//    }


    @PostMapping("editPerson")
    public ModelAndView createPerson(@Valid Person person, BindingResult result) {
        personService.saveOrUpdate(person);
        return new ModelAndView();
    }

    @Secured("ADMIN")
    @GetMapping("/editPerson/delete/id/{id}")
    public ModelAndView delete(@PathVariable Long id, ModelAndView modelAndView) {
        Person person;
        if (personService.existEntry(id)) {
            if (userToPersonRepository.existsUserToPersonByPersonId(id)) {
                UserToPerson userToPerson = userToPersonRepository.getUserToPersonByPersonId(id);
                if (userService.existEntry(userToPerson.getUserId())) {
                    userService.delete(userToPerson.getUserId());
                }
            }
            personService.delete(id);
            modelAndView.setViewName("redirect:/welcome");
        }
        return modelAndView;

    }

    @PostMapping(Pages.VIEW_WELCOME)
    public ModelAndView newPerson(@Valid Person person, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("welcome");
        personService.saveOrUpdate(person);
        modelAndView.addObject("successMessage", "Person " + person.getFirstName() + " " + person.getLastName() + "has been saved successfully");
        modelAndView.setViewName("redirect:/welcome");
        return modelAndView;
    }

    @GetMapping("/viewPerson/id/{id}/addCild")
    public ModelAndView addCild(@PathVariable Long id, ModelAndView modelAndView) {
        if (personService.existEntry(id)) {
            modelAndView.addObject("sourceId", id);
            modelAndView.addObject("typeId", AppAccountTypes.CILD);
            modelAndView.setViewName("redirect:/editPerson/id/0");

        } else
            modelAndView.setViewName("redirect:/welcome");

        return modelAndView;
    }

}
