package softuni.smileShop.unit;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import softuni.smileShop.model.entities.Category;
import softuni.smileShop.model.entities.Product;
import softuni.smileShop.model.entities.Role;
import softuni.smileShop.model.entities.User;
import softuni.smileShop.model.service.CategoryServiceModel;
import softuni.smileShop.model.service.ProductServiceModel;
import softuni.smileShop.model.service.UserServiceModel;
import softuni.smileShop.repository.CategoryRepository;
import softuni.smileShop.repository.ProductRepository;
import softuni.smileShop.repository.UserRepository;
import softuni.smileShop.service.CategoryService;
import softuni.smileShop.service.ProductService;
import softuni.smileShop.service.RoleService;
import softuni.smileShop.service.impl.ProductServiceImpl;
import softuni.smileShop.service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductServiceImpl serviceTestProduct;
    private Product product1, product2;
    private User testUser1, testUser2;
    private Category testCategory1, testCategory2;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @Mock
    private CategoryService mockCategoryService;

    @Mock
    private ProductService mockProductService;

    @BeforeEach
    public void initProduct() {

        testUser1 = new User();
        testUser1.setUsername("user 1");

        testCategory1 = new Category();
        testCategory1.setName("Аccessory");

        product1 = new Product() {
            {
                setId("Some_UUID");
                setName("diadema");
                setPrice(BigDecimal.valueOf(5.55));
                setDescription("Proba");
                setImageUrl("Image 1");
                setUser(testUser1);
                setCategories(List.of(testCategory1));
            }
        };

        mockProductRepository.save(product1);

        testUser2 = new User();
        testUser2.setUsername("user 2");

        testCategory2 = new Category();
        testCategory2.setName("Gift");

        product2 = new Product() {{
            setId("Some_UUID");
            setName("Bucket");
            setPrice(BigDecimal.valueOf(5.55));
            setDescription("Ot bonbon");
            setImageUrl("Image 2");
            setUser(testUser2);
            setCategories(List.of(testCategory2));
        }};
        mockProductRepository.save(product2);

        serviceTestProduct = new ProductServiceImpl(mockProductRepository, mockCategoryRepository, mockCategoryService, new ModelMapper());

    }


    @Test
    public void createProduct_whenValid_productCreated() throws Exception {
        mockProductService.addProduct(new ProductServiceModel() {{
            setCategories(new ArrayList<>() {{
                add(new CategoryServiceModel());
            }});
        }});

        Mockito.verify(mockProductRepository).save(product1);
    }

    @Test
    public void findAllProduct_WhenValid_Product() {

        when(mockProductRepository.findAll()).thenReturn(List.of(product1, product2));

        List<ProductServiceModel> resultTest = serviceTestProduct.findAllProducts();

        Assertions.assertEquals(2, resultTest.size());

        ProductServiceModel productServiceModel1 = resultTest.get(0);
        ProductServiceModel productServiceModel2 = resultTest.get(1);

        Assertions.assertEquals(product1.getName(), productServiceModel1.getName());
        Assertions.assertEquals(product2.getName(), productServiceModel2.getName());
    }

    @Test
    public void deleteCategory_WhenValid_Category(){
        when(mockProductRepository.findAll()).thenReturn(List.of(product1, product2));

        List<ProductServiceModel> resultTest = serviceTestProduct.findAllProducts();

        serviceTestProduct.deleteProduct(product1.getId());
    }


//    @Test
//    public void findByIdProduct_WhenValid_Product()throws IllegalArgumentException  {
//
//        when(mockProductRepository.findAll()).thenReturn(List.of(product1, product2));
//
//        List<ProductServiceModel> resultTest = serviceTestProduct.findAllProducts();
//
//        Assertions.assertEquals(2, resultTest.size());
//
//        ProductServiceModel productServiceModel1 =  serviceTestProduct.findProductById(product1.getId());
//        ProductServiceModel productServiceModel2 =  serviceTestProduct.findProductById(product2.getId());
//
//        Assertions.assertEquals(product1.getId(), productServiceModel1.getId());
//        Assertions.assertEquals(product2.getId(), productServiceModel2.getId());
//    }

}
