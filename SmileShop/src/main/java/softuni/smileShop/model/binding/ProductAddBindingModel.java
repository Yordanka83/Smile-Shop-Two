package softuni.smileShop.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ProductAddBindingModel {

    private String name;
    private MultipartFile image;
    private BigDecimal price;
    private String description;
    private List<String> categories;

    public ProductAddBindingModel() {
    }

    @NotBlank(message = "Cannot be emty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Not empty file")
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @DecimalMin(value = "0", message = "Price must be positive")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Size(min = 3, message = "Description must be min 3 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


   // @NotEmpty(message = "Input list cannot be empty.")
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
