//package softuni.demoprodgect.service.impl;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import softuni.demoprodgect.model.entities.Log;
//import softuni.demoprodgect.model.entities.Product;
//import softuni.demoprodgect.model.entities.User;
//import softuni.demoprodgect.model.service.LogServiceModel;
//import softuni.demoprodgect.repository.LogRepository;
//import softuni.demoprodgect.service.LogService;
//import softuni.demoprodgect.service.ProductService;
//import softuni.demoprodgect.service.UserService;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class LogServiceImpl implements LogService {
//
//    private final LogRepository logRepository;
//    private final ProductService productService;
//    private final UserService userService;
//    private final ModelMapper modelMapper;
//
//    public LogServiceImpl(LogRepository logRepository, ProductService productService, UserService userService, ModelMapper modelMapper) {
//        this.logRepository = logRepository;
//        this.productService = productService;
//        this.userService = userService;
//        this.modelMapper = modelMapper;
//    }
//
//
//    @Override
//    public void createLog(String action, Long productId) {
//        Product product = productService
//                .findProductById(productId);
//
//        Authentication authentication = SecurityContextHolder
//                .getContext()
//                .getAuthentication();
//
//        String username = authentication.getName();
//        User user = userService.findByName(username);
//
//        Log logEntity = new Log()
//                .setProduct(product)
//                .setUser(user)
//                .setAction(action)
//                .setDateTime(LocalDateTime.now());
//
//        logRepository.save(logEntity);
//    }
//
//    @Override
//    public List<LogServiceModel> findAllLogs() {
//        return null;
//    }
//}
