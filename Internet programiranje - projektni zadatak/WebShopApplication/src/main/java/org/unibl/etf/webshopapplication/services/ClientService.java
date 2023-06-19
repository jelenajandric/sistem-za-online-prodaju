package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.*;
import org.unibl.etf.webshopapplication.model.entities.ClientEntity;
import org.unibl.etf.webshopapplication.model.entities.MailToTechnicalSupportEntity;
import org.unibl.etf.webshopapplication.model.entities.PinEntity;
import org.unibl.etf.webshopapplication.repositories.*;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PinRepository pinRepository;
    private final ModelMapper modelMapper;
    private final ActivateAccountService activateAccountService;
    private final AvatarService avatarService;
    private final MailToTechnicalSupportRepository mailToTechnicalSupportRepository;
    private final StatisticService statisticService;

    public ClientService(ClientRepository clientRepository, PinRepository pinRepository, ModelMapper modelMapper,
                         ActivateAccountService activateAccountService, AvatarService avatarService,
                         MailToTechnicalSupportRepository mailToTechnicalSupportRepository, StatisticService statisticService) {
        this.clientRepository = clientRepository;
        this.pinRepository = pinRepository;
        this.modelMapper = modelMapper;
        this.activateAccountService = activateAccountService;
        this.avatarService = avatarService;
        this.mailToTechnicalSupportRepository = mailToTechnicalSupportRepository;
        this.statisticService = statisticService;
    }
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        Client client = modelMapper.map(registrationRequest, Client.class);
        ClientEntity clientEntity = clientRepository.save(modelMapper.map(client, ClientEntity.class));

        statisticService.saveAction(clientEntity.getUsername(), StatisticService.REGISTRATION, clientEntity.getId());

        int pin = activateAccountService.sendPinOnMail(clientEntity.getEmail());
        PinEntity pinEntity = new PinEntity();
        pinEntity.setPin(pin);
        pinEntity.setClientId(clientEntity.getId());
        pinRepository.save(pinEntity);

        return modelMapper.map(clientEntity, RegistrationResponse.class);
    }

    public boolean isUsernameFree(String username) {
        ClientEntity entity = clientRepository.findClientEntityByUsername(username);
        return entity == null;
    }


    public LoginResponse login(LoginRequest loginRequest) {
        ClientEntity clientEntity = clientRepository.findClientEntityByUsername(loginRequest.getUsername());
        if(clientEntity!=null && clientEntity.getPassword().equals(loginRequest.getPassword())) {
//            if(!clientEntity.isActivated()) {
//                int pin = activateAccountService.sendPinOnMail(clientEntity.getEmail());
//                pinRepository.updatePinValue(pin, clientEntity.getId());
//            }
            return modelMapper.map(clientEntity, LoginResponse.class);
        } else {
            return null;
        }
    }

    public MyInfoResponse getMyInfo(String username) {
        ClientEntity clientEntity = clientRepository.findClientEntityByUsername(username);
        clientEntity.setAvatar(avatarService.getAvatarPhoto(username).get("content"));
        return modelMapper.map(clientEntity, MyInfoResponse.class);
    }

    public int updateClient(UpdateRequest updateRequest) {
        Client client = modelMapper.map(updateRequest, Client.class);
        int result = clientRepository.updateClientByUsername(updateRequest.getName(),updateRequest.getSurname(),
                updateRequest.getPassword(),updateRequest.getCity(),updateRequest.getEmail(), updateRequest.getUsername());

        statisticService.saveAction(client.getUsername(), StatisticService.ACCOUNT_UPDATE, clientRepository.getClientIdByUsername(client.getUsername()));
        return result;
    }

    public Boolean changeEmail(ChangeEmailRequest changeEmailRequest) {
        try {
            clientRepository.updateEmail(changeEmailRequest.getEmail(), changeEmailRequest.getUsername());
            int pin = activateAccountService.sendPinOnMail(changeEmailRequest.getEmail());
            pinRepository.updatePinValue(pin, clientRepository.getClientIdByUsername(changeEmailRequest.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void sendEmailToTechnicalSupport(SendEmailToTechnicalSupportRequest request) {
        MailToTechnicalSupportEntity entity = new MailToTechnicalSupportEntity();
        entity.setContent(request.getContent());
        entity.setClientId(clientRepository.getClientIdByUsername(request.getClientUsername()));
        mailToTechnicalSupportRepository.save(entity);
    }

}
