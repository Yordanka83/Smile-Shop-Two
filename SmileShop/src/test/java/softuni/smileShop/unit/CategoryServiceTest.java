package softuni.smileShop.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.smileShop.model.entities.Category;
import softuni.smileShop.model.entities.Product;
import softuni.smileShop.model.service.CategoryServiceModel;
import softuni.smileShop.model.service.ProductServiceModel;
import softuni.smileShop.repository.CategoryRepository;
import softuni.smileShop.service.CategoryService;
import softuni.smileShop.service.impl.CategoryServiceImpl;
import softuni.smileShop.service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    private CategoryServiceImpl serviceTestCategory;

    private Category testCategory1, testCategory2;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @Mock
    private CategoryService mockCategoryService;

    @Mock
    private CategoryServiceModel mockCategoryServiceModel;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    public void initProduct() {
        testCategory1 = new Category();
        testCategory1.setName("Аccessory");

        mockCategoryRepository.save(testCategory1);


        testCategory2 = new Category();
        testCategory2.setName("diadema");

        mockCategoryRepository.save(testCategory2);

        serviceTestCategory = new CategoryServiceImpl(mockCategoryRepository, new ModelMapper());

    }

    @Test
    public void createCategory_whenValid_categoryCreated() {
        serviceTestCategory.addCategory(new CategoryServiceModel());

        Mockito.verify(mockCategoryRepository).save(testCategory2);
    }


    @Test
    public void findAllCategory_WhenValid_Category() {

        when(mockCategoryRepository.findAll()).thenReturn(List.of(testCategory1, testCategory2));

        List<CategoryServiceModel> resultTest = serviceTestCategory.findAllCategories();

        Assertions.assertEquals(2, resultTest.size());

        CategoryServiceModel testCategoryServiceModel1 = resultTest.get(0);
        CategoryServiceModel testCategoryServiceModel2 = resultTest.get(1);

        Assertions.assertEquals(testCategory1.getName(), testCategoryServiceModel1.getName());
        Assertions.assertEquals(testCategory2.getName(), testCategoryServiceModel2.getName());

    }

//    @Test
//    public void when_EditCategory_WhitCorrectValues() {

//  when(mockCategoryRepository.findAll()).thenReturn(List.of(testCategory1, testCategory2));
//
//        List<CategoryServiceModel> resultTest = serviceTestCategory.findAllCategories();
//        CategoryServiceModel categoryService = new CategoryServiceModel();
//
//        CategoryServiceModel testCategory2 = new CategoryServiceModel();
//        testCategory2.setId("UUU");
//        testCategory2.setName("diademaNova");
//
//        CategoryServiceModel actual = serviceTestCategory.editCategory(testCategory2.getId(), testCategory2);
//
//
//        List<CategoryServiceModel> resultTest = serviceTestCategory.findAllCategories();
//
//        Assertions.assertEquals(2, resultTest.size());
//
//        CategoryServiceModel testCategoryServiceModel1 = resultTest.get(0);
//
//        Assertions.assertEquals(testCategoryServiceModel1.getId(), actual.getId());
//        Assertions.assertEquals(testCategoryServiceModel1.getName(), actual.getName());
//
//    }

    @Test
    public void deleteCategory_WhenValid_Category(){
        when(mockCategoryRepository.findAll()).thenReturn(List.of(testCategory1, testCategory2));

        List<CategoryServiceModel> resultTest = serviceTestCategory.findAllCategories();

         serviceTestCategory.deleteCategory(testCategory1.getId());

    }
}
