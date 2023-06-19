package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.webshopapplication.model.ActivateAccountRequest;
import org.unibl.etf.webshopapplication.services.ActivateAccountService;

import javax.validation.Valid;

@RestController
public class ActivateAccountController {
    private final ActivateAccountService activateAccountService;

    public ActivateAccountController(ActivateAccountService activateAccountService) {
        this.activateAccountService = activateAccountService;
    }

    @PostMapping("/activate-account")
    public ResponseEntity<Boolean> activateAccount(@RequestBody @Valid ActivateAccountRequest activateAccountRequest) {
        boolean result = activateAccountService.activateAccount(activateAccountRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/send-pin-again/{username}")
    public void sendPinAgain(@PathVariable("username") String username) {
        activateAccountService.sendPinAgain(username);
    }

}
