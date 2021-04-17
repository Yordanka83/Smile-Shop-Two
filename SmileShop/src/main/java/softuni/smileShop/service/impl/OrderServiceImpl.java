package softuni.smileShop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.smileShop.model.entities.Order;
import softuni.smileShop.model.entities.Product;
import softuni.smileShop.model.entities.User;
import softuni.smileShop.model.service.OrderServiceModel;
import softuni.smileShop.model.service.UserServiceModel;
import softuni.smileShop.repository.OrderRepository;
import softuni.smileShop.repository.ProductRepository;
import softuni.smileShop.service.OrderService;
import softuni.smileShop.service.ProductService;
import softuni.smileShop.service.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, UserService userService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOrder(OrderServiceModel orderServiceModel){
//        UserServiceModel userService = this.userService.findByUsername(name);
//        Product product = productRepository.findById(productId)
//                .orElseThrow();
//        User user = new User();
//        user.setId(userService.getId());
//
//        Order order = new Order();
//        order.setProduct(product);
//        order.setUser(user);
//
//        this.orderRepository.save(order);

        User user = this.modelMapper
                .map(this.userService.findByUsername(orderServiceModel.getUsername()), User.class);

        Product product = this.modelMapper
                .map(this.productService
                        .findProductById(orderServiceModel.getProductId()), Product.class);

        Order order = new Order(product, user, orderServiceModel.getPrice(), orderServiceModel.getDate());

        this.orderRepository.save(order);
    }

    @Override
    public List<Order> findAllOrdersByUserUsername(String username) {
        return this.orderRepository
                .findAllByUserUsername(username);
    }

    @Override
    public List<Order> findProductByCustomer(String username) {
        return this.orderRepository
                .findAllByUserUsername(username);
    }

}
