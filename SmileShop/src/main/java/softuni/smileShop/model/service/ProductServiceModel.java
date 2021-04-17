package softuni.smileShop.model.service;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceModel {
    private String id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private List<CategoryServiceModel> categories;
    private String user;

    public ProductServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CategoryServiceModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryServiceModel> categories) {
        this.categories = categories;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
