package org.unibl.etf.webshopapplication.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.webshopapplication.model.*;
import org.unibl.etf.webshopapplication.services.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping("/add-new")
    public ResponseEntity<AddNewProductResponse> addNewProduct(@RequestBody @Valid AddNewProductRequest request) {
        AddNewProductResponse response = service.addNewProduct(request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-product/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@RequestBody @Valid AddNewProductRequest request, @PathVariable("id") int id) {
        UpdateProductResponse response = service.updateProduct(request, id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-attribute-values/{productId}")
    public ResponseEntity<Boolean> addAttributeValues(@RequestBody Map<String, String> addAttributeValuesRequest, @PathVariable("productId") int productId) {
        boolean result = service.addAttributeValues(addAttributeValuesRequest, productId);
        return ResponseEntity.ok(result);
    }

//    @GetMapping
//    public ResponseEntity<List<GetAllProductsResponse>> getAllProducts() {
//        List<GetAllProductsResponse> allProductsResponse = service.getAllProducts();
//        return ResponseEntity.ok(allProductsResponse);
//    }

    @PostMapping("/filtering")
    public ResponseEntity<ShowAllProductsResponse> getAllProductsWithFilter(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestBody FilterRequest request) {
        Pageable paging = PageRequest.of(page, size);
        ShowAllProductsResponse allProductsResponse = service.getAllProductsWithFilter(request, paging);
        return ResponseEntity.ok(allProductsResponse);
    }

    @GetMapping("/change-to-sold/{id}")
    public void changeProductToSold(@PathVariable("id") int id) {
        service.changeProductToSold(id);
    }

    @GetMapping("/change-to-available/{id}")
    public void changeProductToAvailable(@PathVariable("id") int id) {
        service.changeProductToAvailable(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getOneProduct(@PathVariable("id") int id) {
        GetProductResponse product = service.getOneProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("get-product-for-update/{id}")
    public ResponseEntity<GetProductForUpdate> getProductForUpdate(@PathVariable("id") int id) {
        GetProductForUpdate product = service.getProductForUpdate(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("get-product-for-buying/{id}")
    public ResponseEntity<GetProductForBuying> getProductForBuying(@PathVariable("id") int id) {
        GetProductForBuying product = service.getProductForBuying(id);
        return ResponseEntity.ok(product);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        service.deleteProduct(id);
    }

    @GetMapping("get-locations")
    public ResponseEntity<List<String>> getAllDistinctLocations() {
        return ResponseEntity.ok(service.getAllDistinctLocations());
    }
}
