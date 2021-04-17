package softuni.smileShop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.smileShop.model.entities.Role;
import softuni.smileShop.model.service.RoleServiceModel;
import softuni.smileShop.model.service.UserServiceModel;
import softuni.smileShop.repository.RoleRepository;
import softuni.smileShop.service.RoleService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedRole() {
        if (this.roleRepository.count() == 0) {
            //така запазваме когато нямаме енум за ролите
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
            this.roleRepository.saveAndFlush(new Role("ROLE_MODERATOR"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));

        }
    }

    @Override
    public List<RoleServiceModel> findAllRoles() {
        return this.roleRepository
                .findAll()
                .stream()
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class))
                .collect(Collectors.toList());
    }
    @Override
    public void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers) {

        if (numberOfUsers == 0) {
            userServiceModel
                    .setAuthorities(this.roleRepository
                            .findAll()
                            .stream()
                            .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                            .collect(Collectors.toList()));
        }

    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper
                .map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }
}
