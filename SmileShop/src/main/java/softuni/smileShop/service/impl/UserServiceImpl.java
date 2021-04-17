package softuni.smileShop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.smileShop.model.entities.User;
import softuni.smileShop.model.service.UserServiceModel;
import softuni.smileShop.repository.UserRepository;
import softuni.smileShop.service.RoleService;
import softuni.smileShop.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        this.roleService.seedRole();
        if (this.userRepository.count() == 0) {               // ако юзър репозитори е равно на нула, няма регистриран потребител
            userServiceModel.setAuthorities(this.roleService.findAllRoles());// в юзър сървиз модела запаметяваме всички роли от рол сървиза


        } else {
            userServiceModel.setAuthorities(new ArrayList<>());         // ако ли не в юзърсървиза запаметяваме нов сет
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));   //във юзърсървиза вземи от ауторитито и добави роля юзър(която я вземаме от ролесървиса от ауторито, това ROLE_ автоматично от секюрито)

        }

        User user = this.modelMapper.map(userServiceModel, User.class);       //създаваме нов юзър и чрез моделмапера мапваме от юзърсървиз модела, юзър клас
        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));    //във юзъра сетваме парола като я вземаме от юзърсървиза и я кодираме с бкрипта

        return this.modelMapper
                .map(this.userRepository.saveAndFlush(user), UserServiceModel.class);

    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(u -> this.modelMapper.map(u,UserServiceModel.class))
                .collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        //от юзър репозиторито търсим юзъра, ако не е намерено връщаме съобщение с грешка

    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository
                .findByUsername(username).isPresent();
    }

    @Override
    public User findByName(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }


}
