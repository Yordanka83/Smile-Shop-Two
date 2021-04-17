package softuni.smileShop.model.view;

import org.springframework.security.core.userdetails.User;
import softuni.smileShop.model.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderViewModel {

    private Product product;
    private BigDecimal price;
    private LocalDate localDate;
    private User user;

    public OrderViewModel() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
