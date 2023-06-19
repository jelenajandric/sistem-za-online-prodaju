package org.unibl.etf.adminapp.beans;

import org.unibl.etf.adminapp.dao.AttributeDAO;
import org.unibl.etf.adminapp.dao.CategoryDAO;
import org.unibl.etf.adminapp.dto.Attribute;
import org.unibl.etf.adminapp.dto.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class AttributeBean implements Serializable {

    public ArrayList<Attribute> getAllAttributesForSpecificCategory(String categoryName) {
        return AttributeDAO.getAllAttributesForSpecificCategory(categoryName);
    }

    public boolean findIfAttributeNameIsFree(String name) {
        Attribute attribute = AttributeDAO.findByName(name);
        return attribute == null;
    }

    public boolean deleteAttributeWithSpecificName(String name) {
        return AttributeDAO.deleteAttributeWithSpecificName(name);
    }

    public boolean deleteAttributeWithSpecificCategoryName(String categoryName) {
        return AttributeDAO.deleteAttributeWithSpecificCategoryId(CategoryDAO.findCategoryId(categoryName));
    }

    public boolean updateAttribute(String newName, String oldName) {
        return AttributeDAO.updateAttribute(newName, AttributeDAO.findAttributeId(oldName));
    }

    public boolean addNewAttribute(String name, String categoryName) {
        return AttributeDAO.createNewAttribute(name, CategoryDAO.findCategoryId(categoryName));
    }
}
