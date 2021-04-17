package softuni.smileShop.service;

import softuni.smileShop.model.service.RoleServiceModel;
import softuni.smileShop.model.service.UserServiceModel;

import java.util.List;

public interface RoleService {
    void seedRole();

    void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers);

    List<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);


}
