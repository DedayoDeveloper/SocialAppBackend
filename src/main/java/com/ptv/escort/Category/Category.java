package com.ptv.escort.Category;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "category_details")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "category_name")
     private CategoryName categoryName;

    @Column(name = "price")
    private BigDecimal price;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
