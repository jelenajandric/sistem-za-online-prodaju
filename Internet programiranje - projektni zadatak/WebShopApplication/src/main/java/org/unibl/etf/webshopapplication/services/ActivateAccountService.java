package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.ActivateAccountRequest;
import org.unibl.etf.webshopapplication.model.entities.ClientEntity;
import org.unibl.etf.webshopapplication.repositories.ClientRepository;
import org.unibl.etf.webshopapplication.repositories.PinRepository;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ActivateAccountService {
    private final ClientRepository clientRepository;
    private final PinRepository pinRepository;
    private final SendMailUtil sendMailUtil;
    private final StatisticService statisticService;

    public ActivateAccountService(ClientRepository clientRepository, PinRepository pinRepository, SendMailUtil sendMailUtil, StatisticService statisticService) {
        this.clientRepository = clientRepository;
        this.pinRepository = pinRepository;
        this.sendMailUtil = sendMailUtil;
        this.statisticService = statisticService;
    }

    public boolean activateAccount(ActivateAccountRequest activateAccountRequest) {
        ClientEntity clientEntity = clientRepository.findClientEntityByUsername(activateAccountRequest.getUsername());
        int pin = pinRepository.findPinByClientId(clientEntity.getId());
        if (pin != activateAccountRequest.getPin()) {
            return false;
        }
        clientRepository.activateClientAccount(clientEntity.getUsername());
        pinRepository.deletePinEntityByClientId(clientEntity.getId());

        statisticService.saveAction(clientEntity.getUsername(), StatisticService.ACCOUNT_ACTIVATION, clientEntity.getId());

        return true;
    }

    public int sendPinOnMail(String email) {
        int pin = ThreadLocalRandom.current().nextInt(1000, 10000);
        String title = "ETFBL_IP - aktivacija naloga";
        String content = "Pin za aktivaciju vaseg naloga je: " + pin;
        sendMailUtil.sendPinOnMail(email, content, title);
        return pin;
    }

    public String getEmailForUsername(String username) {
        return clientRepository.findEmailByUsername(username);
    }

    public void sendPinAgain(String username) {
        int pin = sendPinOnMail(getEmailForUsername(username));
        pinRepository.updatePinValue(pin, clientRepository.getClientIdByUsername(username));
    }
}
