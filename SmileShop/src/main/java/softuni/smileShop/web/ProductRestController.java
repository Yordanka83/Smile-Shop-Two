package softuni.smileShop.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.smileShop.model.view.ProductAllViewModel;
import softuni.smileShop.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public ProductRestController(ProductRepository productRepository,
                                 ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("api")
    public ResponseEntity<List<ProductAllViewModel>> findAll(){

        List<ProductAllViewModel> productAllViewModels = productRepository
                .findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductAllViewModel.class))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(productAllViewModels);
    }
}
