package softuni.smileShop.unit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import softuni.smileShop.model.entities.Role;
import softuni.smileShop.model.entities.User;
import softuni.smileShop.model.service.UserServiceModel;
import softuni.smileShop.repository.UserRepository;
import softuni.smileShop.service.RoleService;
import softuni.smileShop.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserServiceImpl serviceTest;
    private User testUser1;
    private User testUser2;
    private Role testRoleAdmin;
    private Role testRoleUser;

    @Mock
    private UserRepository mockedUserRepository;

    @Mock
    private RoleService mockRoleService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;



    @BeforeEach
    public void init() {
        testRoleAdmin = new Role();
        testRoleAdmin.setAuthority("Admin");

        testRoleUser = new Role();
        testRoleUser.setAuthority("User");

        testUser1 = new User() {{
            setId("Some_UUID");
            setUsername("Yordanka");
            setPassword("123");
            setEmail("yordanka@abv.bg");
            setAuthorities(List.of(testRoleAdmin));
        }};
        mockedUserRepository.save(testUser1);

        testUser2 = new User() {{
            setId("Some_UUID");
            setUsername("pesho");
            setPassword("123");
            setEmail("pesho@abv.bg");
            setAuthorities(List.of(testRoleUser));
        }};
        mockedUserRepository.save(testUser2);

        serviceTest = new UserServiceImpl(mockedUserRepository, mockRoleService, new ModelMapper(),mockPasswordEncoder);

    }

    @Test
    public void userService_GetUserWithCorrectUsername_ShouldReturnCorrect() {

        Mockito.when(mockedUserRepository.findByUsername("Yordanka"))
                .thenReturn(Optional.of(testUser1));

        UserServiceModel userModels = serviceTest.findByUsername("Yordanka");

        Assertions.assertEquals(testUser1.getUsername(),userModels.getUsername() );

    }

    @Test
    public void userService_GetAllUserWithCorrectUsername_ShouldReturnCorrect(){

        when(mockedUserRepository.findAll()).thenReturn(List.of(testUser1, testUser2));

        List<UserServiceModel> allUser = serviceTest.findAllUsers();

        Assertions.assertEquals(2, allUser.size());

        UserServiceModel model1 = allUser.get(0);
        UserServiceModel model2 = allUser.get(1);

        Assertions.assertEquals(testUser1.getUsername(), model1.getUsername());
        Assertions.assertEquals(testUser1.getPassword(), model1.getPassword());
        Assertions.assertEquals(testUser1.getEmail(), model1.getEmail());

        Assertions.assertEquals(testUser2.getUsername(), model2.getUsername());
        Assertions.assertEquals(testUser2.getPassword(), model2.getPassword());
        Assertions.assertEquals(testUser2.getEmail(), model2.getEmail());



    }
    @Test
    public void userService_GetUserAndPasswordWitCorrect_ShouldReturnCorrect(){


        Mockito.when(mockedUserRepository.findByUsernameAndPassword("Yordanka", "UUI_Id"))
                .thenReturn(Optional.of(testUser1));

        UserServiceModel userModels = serviceTest.findUserByUsernameAndPassword("Yordanka", "UUI_Id");

        Assertions.assertEquals(testUser1.getUsername(),userModels.getUsername() );
        Assertions.assertEquals(testUser1.getPassword(),userModels.getPassword() );
    }

    @Test
    public void userService_UserExist_ShouldReturnCorrect() {

        Mockito.when(mockedUserRepository.findByUsername("Yordanka"))
                .thenReturn(Optional.of(testUser1));

        boolean current = serviceTest.userExists("Yordanka");
        assert (current);
    }
}
