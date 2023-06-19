package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.repositories.AttributeRepository;

import java.util.List;

@Service
public class AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public List<String> getAllAttributesByCategoryId(int categoryId) {
        return attributeRepository.getAllAttributesByCategoryId(categoryId);
    }
}
