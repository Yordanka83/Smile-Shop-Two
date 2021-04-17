package softuni.smileShop.model.view;

import softuni.smileShop.model.entities.Category;

import java.math.BigDecimal;
import java.util.List;

public class ProductDeleteViewModel {

    private String id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String description;
    private List<Category> categories;

    public ProductDeleteViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

