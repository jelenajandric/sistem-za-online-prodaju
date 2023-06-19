package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.entities.StatisticEntity;
import org.unibl.etf.webshopapplication.repositories.StatisticRepository;

import java.time.LocalDateTime;

@Service
public class StatisticService {

    //actions
    public static final String REGISTRATION = "Registracija novog korisnika";
    public static final String ACCOUNT_ACTIVATION = "Aktivacija naloga korisnika";
    public static final String ACCOUNT_UPDATE = "Azuriranje naloga korisnika";
    public static final String ADDING_PRODUCT = "Dodavanje novog proizvoda";
    public static final String UPDATE_PRODUCT = "Azuriranje proizvoda";
    public static final String DELETE_PRODUCT = "Brisanje proizvoda";
    public static final String BUYING_PRODUCT = "Kupovina proizvoda";
    public static final String CHANGE_PRODUCT_TO_SOLD = "Proizvod oznacen kao kupljen";
    public static final String CHANGE_PRODUCT_TO_AVAILABLE = "Proizvod oznacen kao dostupan";


    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public void saveAction(String clientUsername, String action, int id) {
        LocalDateTime dateTime = LocalDateTime.now();
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setClientUsername(clientUsername);
        statisticEntity.setWhatIsHappened(action);
        statisticEntity.setReturnedId(id);
        statisticEntity.setDate(dateTime);
        statisticRepository.save(statisticEntity);
    }
}
