package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.webshopapplication.model.BuyingProductRequest;
import org.unibl.etf.webshopapplication.services.BuyingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/buying")
public class BuyingController {
    private final BuyingService buyingService;

    public BuyingController(BuyingService buyingService) {
        this.buyingService = buyingService;
    }

    @PostMapping()
    public ResponseEntity<Integer> buyProduct(@RequestBody @Valid BuyingProductRequest request) {
        Integer result = buyingService.buyProduct(request);
        return ResponseEntity.ok(result);
    }

}
