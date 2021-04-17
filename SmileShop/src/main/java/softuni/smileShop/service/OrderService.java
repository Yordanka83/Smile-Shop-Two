package softuni.smileShop.service;
import softuni.smileShop.model.entities.Order;
import softuni.smileShop.model.service.OrderServiceModel;


import java.util.List;

public interface OrderService {

    void createOrder(OrderServiceModel orderServiceModel);

    List<Order> findAllOrdersByUserUsername(String username);

    List<Order>findProductByCustomer(String username);
}
