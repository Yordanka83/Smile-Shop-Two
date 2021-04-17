package softuni.smileShop.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.smileShop.error.ProductNotFoundException;
import softuni.smileShop.model.entities.Category;
import softuni.smileShop.model.entities.Product;
import softuni.smileShop.model.service.ProductServiceModel;
import softuni.smileShop.model.view.ProductAllViewModel;
import softuni.smileShop.repository.CategoryRepository;
import softuni.smileShop.repository.ProductRepository;
import softuni.smileShop.service.CategoryService;
import softuni.smileShop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {

        List<Category> categories = categoryRepository.findAll();

        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setCategories(categories);
        this.productRepository.saveAndFlush(product);

        return productServiceModel;

    }


    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository
                .findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductServiceModel findProductById(String id) {
        return this.productRepository
                .findById(id)
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel) {

        Product product = this.productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with the given id was not found!"));

        product.setName(productServiceModel.getName());
        product.setDescription(productServiceModel.getDescription());
        product.setPrice(productServiceModel.getPrice());
        product.setCategories(productServiceModel
                .getCategories()
                .stream()
                .map(c -> this.modelMapper.map(c, Category.class))
                .collect(Collectors.toList()));

        return this.modelMapper.map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);
    }

    @Override
    public void deleteProduct(String id) {
        this.productRepository
                .deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository
                .findById(String.valueOf(id))
                .orElseThrow(IllegalArgumentException::new);
    }

////    @Override
////    public void findByCategory() {
////        ProductAllViewModel productAllViewModel = this.modelMapper.map(productService.findProductById(id), ProductAllViewModel.class);
////
////        switch (productAllViewModel.getCategory()) {
////            case "Accessories":
////                model.("products", productAllViewModel);
////                break;
////            case "Gift":
////                model.addAttribute("products", productAllViewModel);
////
////                break;
////            case "Bouquets":
////                model.addAttribute("products", productAllViewModel);
////
////                break;
////            case "Martenitsi":
////                model.addAttribute("products", productAllViewModel);
////                break;
////        }
//    @Override
//    public List<ProductAllViewModel> findProductByCategory(String nameCategory) {
//
//        if (nameCategory.equals("Bouquets")) {
//           productAllViewModel.s
//        } else if (nameCategory.equals("Martenitsi")) {
//            return this.productRepository
//                    .findAllByCategoriesContains(nameCategory)
//                    .stream()
//                    .map(category -> modelMapper.map(category, ProductAllViewModel.class))
//                    .collect(Collectors.toList());
//        } else if (nameCategory.equals("Accessories")) {
//            return this.productRepository
//                    .findAllByCategoriesContains(nameCategory)
//                    .stream()
//                    .map(category -> modelMapper.map(category, ProductAllViewModel.class))
//                    .collect(Collectors.toList());
//        } else if (nameCategory.equals("Gift")) {
//            return this.productRepository
//                    .findAllByCategoriesContains(nameCategory)
//                    .stream()
//                    .map(category -> modelMapper.map(category, ProductAllViewModel.class))
//                    .collect(Collectors.toList());
//        }
//        return ;
//    }

}

