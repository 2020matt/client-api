package za.co.api.processor.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.api.processor.client.model.Client;
import za.co.api.processor.client.repository.ClientRepository;
import za.co.api.processor.client.validation.Utilities;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ClientRestController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clients/{name}")
    public ResponseEntity<Client> getClientByfirstName(@PathVariable("name") String firstName) {
        try {
            Optional<Client> clients = clientRepository.findByFirstName(firstName);
            if (clients.isPresent()) {
                return new ResponseEntity<>(clients.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clients/id")
    public ResponseEntity<Client> getClientByIdNumber(@RequestParam(required = true) Long id) {
        try {
            Optional<Client> clients = Optional.of(new Client());
            if (Utilities.validateId(id)) { //pre-validation
                clients = clientRepository.findByIdNumber(id);
            } else {
                log.error("Incorrect IdNumber[" + id + "]");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (clients.isPresent()) {
                return new ResponseEntity<>(clients.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @GetMapping("/clients/phoneNumber")
    public ResponseEntity<Client> getClientByPhoneNumber(@RequestParam(required = true) String pNumber) {
        try {
            Optional<Client> clients = Optional.of(new Client());
            if (Utilities.validatePhoneNumber(pNumber)) {
                clients = clientRepository.findByMobileNumber(pNumber);
            } else {
                log.error("Incorrect phoneNumber[" + pNumber + "]");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (clients.isPresent()) {
                return new ResponseEntity<>(clients.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
