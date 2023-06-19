package org.unibl.etf.webshopapplication.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.BuyingProduct;
import org.unibl.etf.webshopapplication.model.BuyingProductRequest;
import org.unibl.etf.webshopapplication.model.entities.BuyingEntity;
import org.unibl.etf.webshopapplication.repositories.BuyingRepository;
import org.unibl.etf.webshopapplication.repositories.ClientRepository;
import org.unibl.etf.webshopapplication.repositories.ProductRepository;

import java.sql.Date;

@Service
public class BuyingService {
    private final BuyingRepository buyingRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final StatisticService statisticService;
    private final ModelMapper modelMapper;

    public BuyingService(BuyingRepository buyingRepository, ClientRepository clientRepository, ProductRepository productRepository, StatisticService statisticService, ModelMapper modelMapper) {
        this.buyingRepository = buyingRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.statisticService = statisticService;
        this.modelMapper = modelMapper;
    }

    public Integer buyProduct(BuyingProductRequest request) {
        int clientId = clientRepository.getClientIdByUsername(request.getClientUsername());
        BuyingProduct buyingProduct = new BuyingProduct(request.getProductId(), clientId, request.getPaymentMethod(), new Date(System.currentTimeMillis()));
        BuyingEntity entity = modelMapper.map(buyingProduct, BuyingEntity.class);
        productRepository.changeProductState(request.getProductId(), true);
        entity = buyingRepository.save(entity);

        statisticService.saveAction(request.getClientUsername(), StatisticService.BUYING_PRODUCT, request.getProductId());

        return entity.getId();
    }
}
