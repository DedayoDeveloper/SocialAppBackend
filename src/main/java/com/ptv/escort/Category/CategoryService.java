package com.ptv.escort.Category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryService {



    @Autowired
    private CategoryRepository categoryRepository;

    private Category addCategory(CategoryName categoryName, BigDecimal price){
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setPrice(price);
        return categoryRepository.save(category);
    }



    public String categories(){
        CategoryName categoryName = CategoryName.FriendWithBenefit;
        BigDecimal price = BigDecimal.valueOf(5000);
        addCategory(categoryName,price);

        CategoryName categoryName2 = CategoryName.PartyStarters;
        BigDecimal price2 = BigDecimal.valueOf(3000);
        addCategory(categoryName2,price2);

        CategoryName categoryName3 = CategoryName.Relationship;
        BigDecimal price3 = BigDecimal.valueOf(5000);
        addCategory(categoryName3, price3);

        CategoryName categoryName4 = CategoryName.SexHookUp;
        BigDecimal price4 = BigDecimal.valueOf(5000);
        addCategory(categoryName4,price4);

        CategoryName categoryName5 = CategoryName.Strippers;
        BigDecimal price5 = BigDecimal.valueOf(5000);
        addCategory(categoryName5,price5);

        CategoryName categoryName6 = CategoryName.SugarDaddy;
        BigDecimal price6 = BigDecimal.valueOf(10000);
        addCategory(categoryName6,price6);

        CategoryName categoryName7 = CategoryName.SugarMummy;
        BigDecimal price7 = BigDecimal.valueOf(15000);
        addCategory(categoryName7,price7);

        return "successful";
    }

    public Category getCategoryDetails(CategoryName categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Category> getCategoryPrices() {
        return categoryRepository.findAll();
    }
}
