package softuni.smileShop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.smileShop.model.entities.User;
import softuni.smileShop.model.service.UserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername (String username);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    List<UserServiceModel> findAllUsers();

    boolean userExists(String username);


    User findByName(String username);

}

