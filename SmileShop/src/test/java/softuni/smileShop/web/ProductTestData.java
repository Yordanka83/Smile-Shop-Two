package softuni.smileShop.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.smileShop.model.entities.Category;
import softuni.smileShop.model.entities.Product;
import softuni.smileShop.model.entities.User;
import softuni.smileShop.repository.ProductRepository;
import softuni.smileShop.service.CategoryService;
import softuni.smileShop.service.CloudinaryService;
import softuni.smileShop.service.ProductService;
import softuni.smileShop.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class ProductTestData {

    private Product product1, product2;
    private User testUser1, testUser2;
    private Category testCategory1, testCategory2;

    private ProductService productServiceTest;
    private ProductRepository productRepositoryTest;
    private ModelMapper modelMapperTest;
    private CategoryService categoryServiceTest;

    public ProductTestData(ProductService productServiceTest, ModelMapper modelMapperTest, CategoryService categoryServiceTest) {
        this.productServiceTest = productServiceTest;
        this.modelMapperTest = modelMapperTest;
        this.categoryServiceTest = categoryServiceTest;
    }

    public void init() {
        testUser1 = new User();
        testUser1.setUsername("user 1");

        testCategory1 = new Category();
        testCategory1.setName("Аccessory");

        Product product1 = new Product();
        product1.setId("Some_UUID");
        product1.setName("diadema");
        product1.setPrice(BigDecimal.valueOf(2.00));
        product1.setImageUrl("https://res.cloudinary.com/danidani83/image/upload/v1618308426/wdj71h110cji1911aizh.jpg");
        product1.setDescription("For small baby");
        product1.setUser(testUser1);
        product1.setCategories(List.of(testCategory1));

        productRepositoryTest.save(product1);

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

        product2 = productRepositoryTest.save(product2);
//
//        testProduct = product2.getId();
//
//        serviceTestProduct = new ProductServiceImpl(mockProductRepository, mockCategoryRepository, new ModelMapper());

    }
    void cleanUp(){
        productRepositoryTest.deleteAll();

    }
}
