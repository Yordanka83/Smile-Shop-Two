package softuni.smileShop.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OrderBindingModel {

    private String productId;
    private String nameCustomer;
    private String nameProduct;
    private String description;
    private String price;
    private String date;

    public OrderBindingModel() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @NotBlank(message = "Cannot be emty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    @NotBlank(message = "Cannot be emty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @Size(min = 6, message = "Description must be min 6 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0", message = "Price must be positive")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
