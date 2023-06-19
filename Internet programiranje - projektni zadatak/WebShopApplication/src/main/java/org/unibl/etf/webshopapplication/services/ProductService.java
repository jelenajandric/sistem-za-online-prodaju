package org.unibl.etf.webshopapplication.services;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.*;
import org.unibl.etf.webshopapplication.model.entities.*;
import org.unibl.etf.webshopapplication.repositories.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ClientRepository clientRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeValuesRepository attributeValuesRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final BuyingRepository buyingRepository;
    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final StatisticService statisticService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          ClientRepository clientRepository, AttributeRepository attributeRepository,
                          AttributeValuesRepository attributeValuesRepository, ImageRepository imageRepository,
                          CommentRepository commentRepository, BuyingRepository buyingRepository, ModelMapper modelMapper,
                          ImageService imageService, StatisticService statisticService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.clientRepository = clientRepository;
        this.attributeRepository = attributeRepository;
        this.attributeValuesRepository = attributeValuesRepository;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
        this.buyingRepository = buyingRepository;
        this.modelMapper = modelMapper;
        this.imageService = imageService;
        this.statisticService = statisticService;
    }


    public AddNewProductResponse addNewProduct(AddNewProductRequest request) {
        CategoryEntity categoryEntity = categoryRepository.getCategoryEntityByName(request.getCategoryName());

        int clientId = clientRepository.getClientIdByUsername(request.getClientUsernameSelling());

        Product product = new Product(request.getTitle(), request.getDescription(), request.getPrice(), request.isNew(), request.getLocation(), request.getContact(), categoryEntity.getId(), clientId);
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        productEntity.setId(null);
        productEntity = productRepository.save(productEntity);

        AddNewProductResponse response = new AddNewProductResponse();
        response.setId(productEntity.getId());
        response.setAttributes((ArrayList<String>) attributeRepository.getAllAttributesByCategoryId(categoryEntity.getId()));

        statisticService.saveAction(request.getClientUsernameSelling(), StatisticService.ADDING_PRODUCT, productEntity.getId());

        return response;
    }

    public UpdateProductResponse updateProduct(AddNewProductRequest request, int id) {
        CategoryEntity categoryEntity = categoryRepository.getCategoryEntityByName(request.getCategoryName());
        boolean isCategorySame = productRepository.getCategoryIdByProductId(id) == categoryEntity.getId();

        productRepository.updateProductById(id, request.getTitle(), request.getDescription(), request.getPrice(), request.isNew(),
                request.getLocation(), request.getContact(), categoryEntity.getId());
        UpdateProductResponse response = new UpdateProductResponse();
        response.setId(id);
        HashMap<String, String> attributeValues = new HashMap<>();

        statisticService.saveAction(request.getClientUsernameSelling(), StatisticService.UPDATE_PRODUCT, id);

        if (isCategorySame) {
            List<AttributeValuesEntity> attributeValuesEntities = attributeValuesRepository.getAttributeValuesEntitiesByProductId(id);
            attributeValuesEntities.forEach(attributeValuesEntity -> {
                String attributeName = attributeRepository.getAttributeName(attributeValuesEntity.getAttributeId());
                attributeValues.put(attributeName, attributeValuesEntity.getValue());
            });
        } else {
            List<String> attributes = attributeRepository.getAllAttributesByCategoryId(categoryEntity.getId());
            attributes.forEach(attribute -> attributeValues.put(attribute, ""));
            attributeValuesRepository.deleteByProductId(id);
        }
        response.setAttributesAndValues(attributeValues);
        return response;
    }


    public GetProductResponse getOneProductById(int id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        GetProductResponse response = null;
        Map<String, String> attributeValues = new HashMap<>();
        List<GetAllImagesResponse> productImages;

        List<AttributeValuesEntity> attributeValuesEntities = attributeValuesRepository.getAttributeValuesEntitiesByProductId(id);
        attributeValuesEntities.forEach(attributeValuesEntity -> {
            String attributeName = attributeRepository.getAttributeName(attributeValuesEntity.getAttributeId());
            attributeValues.put(attributeName, attributeValuesEntity.getValue());
        });

        productImages = imageService.getAllImagesForProduct(id);

        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();

            response = new GetProductResponse(productEntity.getId(), productEntity.getTitle(),
                    productEntity.getDescription(), productEntity.getPrice(), productEntity.isNew(), productEntity.getLocation(),
                    productEntity.getContact(), productEntity.isSold(), categoryRepository.getCategoryNameById(productEntity.getId()),
                    clientRepository.getClientUsernameById(productEntity.getClientIdSelling()), productImages, attributeValues);

        }
        return response;
    }

    public boolean addAttributeValues(Map<String, String> addAttributeValuesRequest, int productId) {
        JSONObject jsonObject = new JSONObject(addAttributeValuesRequest);
        List<AttributeEntity> attributes = attributeRepository.findAttributeEntitiesByCategoryId
                (productRepository.findCategoryIdByProductId(productId));
        attributes.forEach(attributeEntity -> attributeValuesRepository.save(new AttributeValuesEntity
                (attributeEntity.getId(), productId, jsonObject.getString(attributeEntity.getName()))));
        return true;
    }

    public void deleteProduct(int id) {
        String sellerUsername = productRepository.findSellerUsername(id);
        imageRepository.deleteByProductId(id);
        commentRepository.deleteByProductId(id);
        buyingRepository.deleteBuyingByProductId(id);
        attributeValuesRepository.deleteByProductId(id);
        productRepository.deleteById(id);
        statisticService.saveAction(sellerUsername, StatisticService.DELETE_PRODUCT, id);

    }

    public GetProductForUpdate getProductForUpdate(int id) {
        GetProductForUpdate response = null;
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();
            response = new GetProductForUpdate(productEntity.getId(), productEntity.getTitle(),
                    productEntity.getDescription(), productEntity.getPrice(), productEntity.isNew() ? "Nov" : "Polovan", productEntity.getLocation(),
                    productEntity.getContact(), categoryRepository.getCategoryNameById(productEntity.getCategoryId()),
                    clientRepository.getClientUsernameById(productEntity.getClientIdSelling()));
        }
        return response;
    }

    public void changeProductToSold(int id) {
        productRepository.changeProductState(id, true);
        String username = productRepository.findSellerUsername(id);
        statisticService.saveAction(username, StatisticService.CHANGE_PRODUCT_TO_SOLD, id);
    }

    public void changeProductToAvailable(int id) {
        productRepository.changeProductState(id, false);
        String username = productRepository.findSellerUsername(id);
        statisticService.saveAction(username, StatisticService.CHANGE_PRODUCT_TO_AVAILABLE, id);
    }

    public GetProductForBuying getProductForBuying(int id) {
        GetProductForBuying productForBuying = new GetProductForBuying();
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            productForBuying.setId(id);
            productForBuying.setTitle(productEntityOptional.get().getTitle());
            productForBuying.setImage(imageService.getImageForPath(imageRepository.findOneByProductId(id)));
            productForBuying.setSold(productEntityOptional.get().isSold());
        }
        return productForBuying;
    }

    public ShowAllProductsResponse findAllWithPaging(Pageable pageable) {
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        Map<Integer, String> imagesAndProductIds = new HashMap<>();
        productEntityPage.get().forEach(productEntity -> {
            String image = imageService.getOneImageForProduct(productEntity.getId());
            imagesAndProductIds.put(productEntity.getId(), image);
        });
        return new ShowAllProductsResponse(productEntityPage, imagesAndProductIds);
    }

    public ShowAllProductsResponse getAllProductsWithFilter(FilterRequest request, Pageable pageable) {
        if (request == null) {
            return findAllWithPaging(pageable);
        }
        List<List<Integer>> listOfProductIds = new ArrayList<>();
        List<Integer> intersectedIds = new ArrayList<>();
        List<Integer> allIds = productRepository.findAllIds();
        boolean isMapWithAttributesEmpty = true;
        if (request.getAttributesAndValues() != null) {
            isMapWithAttributesEmpty = request.getAttributesAndValues().isEmpty();
            if (!isMapWithAttributesEmpty) {
                List<AttributeEntity> attributeEntities = attributeRepository.findAttributeEntitiesByCategoryId(request.getCategoryId());
                attributeEntities.forEach(element -> {
                    if (request.getAttributesAndValues().get(element.getName()) != null) {
                        listOfProductIds.add(attributeValuesRepository.findAllDistinctProductIdsWithCondition
                                (element.getId(), request.getAttributesAndValues().get(element.getName())));
                    }
                });

                //System.out.println(listOfProductIds);

                intersectedIds = listOfProductIds.get(0);
                for (int i = 1; i < listOfProductIds.size(); i++) {
                    intersectedIds.retainAll(listOfProductIds.get(i));
                }
            }
        }

        QProductEntity qProductEntity = QProductEntity.productEntity;
        BooleanExpression productWithIdGTOne = qProductEntity.id.goe(0);
        BooleanExpression productWithTitleLike = request.getTitle() != null ?
                qProductEntity.title.containsIgnoreCase(request.getTitle()) : null;
        BooleanExpression productWithLocation = request.getLocation() != null ?
                qProductEntity.location.eq(request.getLocation()) : null;
        BooleanExpression productWithCategoryId = request.getCategoryId() != null ?
                qProductEntity.categoryId.eq(request.getCategoryId()) : null;
        BooleanExpression priceFrom = request.getPriceFrom() != null ?
                qProductEntity.price.goe(request.getPriceFrom()) : null;
        BooleanExpression priceTo = request.getPriceTo() != null ?
                qProductEntity.price.loe(request.getPriceTo()) : null;
        BooleanExpression isNew = request.getNewOrUsedOrAll() != null ?
                qProductEntity.isNew.eq(request.getNewOrUsedOrAll().equals("new")) : null;
        BooleanExpression isClientId = request.getClientId() != null ?
                qProductEntity.clientIdSelling.eq(request.getClientId()) : null;
        BooleanExpression isSold = request.getIsSold() != null ?
                qProductEntity.isSold.eq(request.getIsSold()) : null;
        BooleanExpression idInListIds = qProductEntity.id.in(isMapWithAttributesEmpty ? allIds : intersectedIds);

        //System.out.println(idInListIds);
        if (request.getNewOrUsedOrAll() != null && request.getNewOrUsedOrAll().equals("all")) {
            isNew = null;
        }
        //listOfProductIds.forEach(System.out::println);

        Page<ProductEntity> productEntityPage = productRepository.findAll(productWithIdGTOne
                .and(productWithTitleLike)
                .and(productWithLocation)
                .and(productWithCategoryId)
                .and(priceFrom)
                .and(priceTo)
                .and(isNew)
                .and(isClientId)
                .and(isSold)
                .and(idInListIds), pageable);
        Map<Integer, String> imagesAndProductIds = new HashMap<>();
        productEntityPage.get().forEach(productEntity -> {
            String image = imageService.getOneImageForProduct(productEntity.getId());
            imagesAndProductIds.put(productEntity.getId(), image);
        });
        //System.out.println(productEntityPage);
        return new ShowAllProductsResponse(productEntityPage, imagesAndProductIds);
    }

    public List<String> getAllDistinctLocations() {
        return productRepository.findAllLocations();
    }
}
