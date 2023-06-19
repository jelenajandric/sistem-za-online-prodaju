package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.webshopapplication.model.*;
import org.unibl.etf.webshopapplication.services.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse response = clientService.register(registrationRequest);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/is-username-free")
    public ResponseEntity<Boolean> isUsernameFree(@RequestParam String username) {
        return ResponseEntity.ok(clientService.isUsernameFree(username));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = clientService.login(loginRequest);
        if (response == null) {
            return ResponseEntity.status(203).build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-my-info")
    public ResponseEntity<MyInfoResponse> getMyInfo(@RequestParam String username) {
        MyInfoResponse myInfoResponse = clientService.getMyInfo(username);
        return ResponseEntity.ok(myInfoResponse);
    }

    @PostMapping("/update-account")
    public ResponseEntity<Integer> updateClient(@RequestBody @Valid UpdateRequest updateRequest) {
        int result = clientService.updateClient(updateRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change-email")
    public ResponseEntity<Boolean> changeEmail(@RequestBody @Valid ChangeEmailRequest changeEmailRequest) {
        return ResponseEntity.ok(clientService.changeEmail(changeEmailRequest));
    }

    @PostMapping("/send-mail")
    public void sendEmailToTechnicalSupport(@RequestBody @Valid SendEmailToTechnicalSupportRequest request) {
        clientService.sendEmailToTechnicalSupport(request);
    }

}
