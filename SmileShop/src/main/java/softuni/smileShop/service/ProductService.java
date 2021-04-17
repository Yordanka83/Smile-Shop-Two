package softuni.smileShop.service;

import softuni.smileShop.model.entities.Product;
import softuni.smileShop.model.service.ProductServiceModel;
import softuni.smileShop.model.view.ProductAllViewModel;


import java.util.List;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductById(String id);

    ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel);

    void deleteProduct(String id);

    Product findById(Long id);

  //  List<ProductAllViewModel> findProductByCategory(String nameCategory);

    //   Product findProductById(Long productId);


}