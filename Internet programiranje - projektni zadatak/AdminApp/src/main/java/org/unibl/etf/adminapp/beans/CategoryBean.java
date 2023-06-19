package org.unibl.etf.adminapp.beans;

import org.unibl.etf.adminapp.dao.CategoryDAO;
import org.unibl.etf.adminapp.dto.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class CategoryBean implements Serializable{

    public ArrayList<Category> getAll() { //da li da u ovoj klasi napravim kao atribut listu categorija
        return CategoryDAO.getAllCategories();
    }

    public boolean findIfCategoryNameIsFree(String name) {
        Category category = CategoryDAO.findByName(name);
        return category == null;
    }


    public boolean deleteCategory(String name) {
        return CategoryDAO.deleteCategory(name);
    }

    public boolean updateCategory(String newName, String oldName) {
        ArrayList<Category> categories = getAll();
        Optional<Category> category= categories.stream().filter(category1 -> {
            return category1.getName().equals(oldName);
        }).findFirst();
        return category.filter(value -> CategoryDAO.updateCategory(newName, value.getId())).isPresent();
    }

    public boolean addNewCategory(String name) {
        return CategoryDAO.createNewCategory(name);
    }

    public int findCategoryId(String name) {
        return CategoryDAO.findCategoryId(name);
    }
}
