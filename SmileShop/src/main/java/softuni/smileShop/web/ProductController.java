package softuni.smileShop.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.smileShop.model.binding.ProductAddBindingModel;
import softuni.smileShop.model.service.CategoryServiceModel;
import softuni.smileShop.model.service.ProductServiceModel;
import softuni.smileShop.model.view.ProductDetailsViewModel;
import softuni.smileShop.service.CategoryService;
import softuni.smileShop.service.CloudinaryService;
import softuni.smileShop.service.ProductService;
import softuni.smileShop.model.view.ProductAllViewModel;
import softuni.smileShop.model.view.ProductDeleteViewModel;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;

    public ProductController(ProductService productService, ModelMapper modelMapper, CategoryService categoryService, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
    }


    @ModelAttribute("productAddBindingModel")
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }


    @GetMapping("/add")
    public String addProduct(Model model) {

        model.addAttribute("category", categoryService.findAllCategoryPro());

        return "product/product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails principal) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoryAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);

            return "redirect:/add";
        }

        ProductServiceModel productServiceModel = modelMapper.map(productAddBindingModel, ProductServiceModel.class);
        productServiceModel.setUser(principal.getUsername());
        productServiceModel.setImageUrl(this.cloudinaryService.uploadImage(productAddBindingModel.getImage()));

        productService.addProduct(productServiceModel);

        return "redirect:/products/all";
    }

    @GetMapping("/all")
    public String allProduct(Model model) {

        List<ProductAllViewModel> products = this.productService
                .findAllProducts()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("products", products);

        return "/product/product-all";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, Model model) {

        ProductDetailsViewModel productDetailsViewModel = this.modelMapper
                .map(this.productService.findProductById(id), ProductDetailsViewModel.class);

        model.addAttribute("category", categoryService.findAllCategoryPro());

        model.addAttribute("product", productDetailsViewModel);

        return "/product/product-edit";

    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, ProductAddBindingModel model) {

        ProductServiceModel productServiceModel = this.modelMapper
                .map(model, ProductServiceModel.class);

        this.productService.editProduct(id, productServiceModel);

        return "redirect:/products/details/" + id;
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, Model model) {

        ProductDeleteViewModel productDeleteViewModel = this.modelMapper
                .map(this.productService.findProductById(id), ProductDeleteViewModel.class);

        model.addAttribute("category", categoryService.findAllCategoryPro());

        model.addAttribute("product", productDeleteViewModel);

        return "/product/product-delete";

    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, ProductDeleteViewModel model) {

        ProductServiceModel productServiceModel = this.modelMapper
                .map(model, ProductServiceModel.class);

        this.productService.deleteProduct(id);

        return "redirect:/products/all";

    }

    @GetMapping("/details/{id}")
    public String detailsProduct(@PathVariable String id, Model model) {

        ProductDetailsViewModel productDetailsViewModel = this.modelMapper.map(productService.findProductById(id), ProductDetailsViewModel.class);

        model.addAttribute("product", productDetailsViewModel);

        return "/product/products-details";
    }

    @GetMapping("/customer")
    public String productAllByCustomer(Model model) {
        List<ProductAllViewModel> products = this.productService
                .findAllProducts()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("products", products);

        return "/product/all-product-customer";
    }


//    @GetMapping("/product-customer/{id}")
//    public String productByNameCategory(@PathVariable String id, Model model) {
//
//        ProductAllViewModel productAllViewModel = this.modelMapper.map(productService.findProductById(id), ProductAllViewModel.class);
//
//
//        model.addAttribute("product", productAllViewModel);
//
//        return "/product/product-edit" + id;
//    }
//
//
//    @PostMapping("/product-customer/{id}")
//    public String productByNameCategory(@PathVariable String id, ProductAddBindingModel model) {
//
//        ProductAllViewModel productAllViewModel = this.modelMapper.map(productService.findProductById(id), ProductAllViewModel.class);
//
//       this.productService.findByCategory();
//
//        return "/product/all-product-customer";
//
//    }
}
