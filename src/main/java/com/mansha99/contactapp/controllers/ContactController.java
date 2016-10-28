package com.mansha99.contactapp.controllers;

import com.mansha99.contactapp.dto.ContactDTO;
import com.mansha99.contactapp.models.Contact;
import com.mansha99.contactapp.utils.MsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mansha99.contactapp.services.ContactService;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/players")
public class ContactController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContactDTO> create(@RequestBody Contact model) {
        System.out.println("======== Name["+model.getName()+"]================");
        ContactDTO dto = new ContactDTO();
        dto.setModel(model);
        dto.setErrors(MsValidator.validate(model));
        if (dto.isValid()) {
            try {
                contactService.save(model);
            } catch (Exception ex) {
                dto.setStatus("fail");
                dto.setMessage(ex.getMessage());
            }
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContactDTO> update(@PathVariable Long id, Contact model) {
        this.validatePlayer(id);
        model.setId(id);
        ContactDTO dto = new ContactDTO();
        dto.setModel(model);
        dto.setErrors(MsValidator.validate(model));
        if (dto.isValid()) {
            try {
                contactService.save(model);
            } catch (Exception ex) {
                dto.setStatus("fail");
                dto.setMessage(ex.getMessage());
            }
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Contact find(@PathVariable Long id) {
        return this.validatePlayer(id);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Contact delete(@PathVariable Long id) {
        Contact model = this.validatePlayer(id);
        this.contactService.delete(id);
        return model;
    }

    //http://localhost:8080/players?page=0&size=3&sort=id,desc
    @RequestMapping(value = "", method = RequestMethod.GET)
    Page<Contact> Read(Pageable pageable) {
        return this.contactService.findAll(pageable);
    }

    private Contact validatePlayer(Long id) {
        Contact player = this.contactService.findOne(id);
        if (player == null) {
            throw new PlayerNotFoundException(id);
        }
        return player;
    }
    // Private fields
    @Autowired
    private ContactService contactService;

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super("Cannot find Player : " + id);
    }
}
