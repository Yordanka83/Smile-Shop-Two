package softuni.smileShop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.smileShop.model.entities.Category;
import softuni.smileShop.model.service.CategoryServiceModel;
import softuni.smileShop.repository.CategoryRepository;
import softuni.smileShop.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addCategory(CategoryServiceModel categoryServiceModel) {

        this.categoryRepository
                .saveAndFlush(this.modelMapper
                        .map(categoryServiceModel, Category.class));

    }



    @Override
    public List<CategoryServiceModel> findAllCategories() {

        return this.categoryRepository
                .findAll()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllCategoryPro() {

        return categoryRepository.allCategoryName();
    }

    @Override
    public CategoryServiceModel findCategoryById(String id) {

        return this.categoryRepository
              .findById(id)
              .map(cat -> this.modelMapper.map(cat, CategoryServiceModel.class))
              .orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public CategoryServiceModel editCategory(String id, CategoryServiceModel categoryServiceModel) {
      Category category = this.categoryRepository
              .findById(id)
              .orElseThrow(IllegalArgumentException::new);

      category.setName(categoryServiceModel.getName());

        return this.modelMapper
                .map(this.categoryRepository.saveAndFlush(category), CategoryServiceModel.class);

    }

    @Override
    public void deleteCategory(String id) {
        this.categoryRepository.deleteById(id);

    }
}
