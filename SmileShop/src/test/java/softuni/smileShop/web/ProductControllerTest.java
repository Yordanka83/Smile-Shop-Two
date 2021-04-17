package softuni.smileShop.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import softuni.smileShop.service.CategoryService;
import softuni.smileShop.service.CloudinaryService;
import softuni.smileShop.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ProductControllerTest {

    private long testProductId;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CloudinaryService cloudinaryService;


    @Autowired
    private MockMvc mockMvc;

    private ProductTestData testData;

    @BeforeEach
    public void setup(){
        testData = new  ProductTestData(productService, new ModelMapper(), categoryService);
        testData.init();


    }
//    @Test
//    @WithMockUser(value = "dani", roles = {"USER", "ADMIN"})
//    void testShouldredurnStatusView() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("/products"+ "/details/{id}", testProductId))
//                .andExpect(status().isOk())
//                .andExpect(view().name("client-view"))
//                .andExpect(model().attributeExists("client"));
//    }


}
