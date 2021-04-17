package softuni.smileShop.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.smileShop.model.binding.CategoryAddBindingModel;
import softuni.smileShop.model.service.CategoryServiceModel;
import softuni.smileShop.model.view.CategoryDeleteViewModel;
import softuni.smileShop.model.view.CategoryEditViewModel;
import softuni.smileShop.model.view.CategoryViewModel;
import softuni.smileShop.model.view.ProductAllViewModel;
import softuni.smileShop.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String addCategory() {

        return "category/category-add";
    }

    @ModelAttribute("categoryAddBindingModel")
    public CategoryAddBindingModel createBindingModel() {
        return new CategoryAddBindingModel();
    }


    @PostMapping("/add")
    public String addCategoryConfirm(@Valid CategoryAddBindingModel categoryAddBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoryAddBindingModel", categoryAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryAddBindingModel", bindingResult);

            return "redirect:/add";
        }

        CategoryServiceModel categoryServiceModel = modelMapper.map(categoryAddBindingModel, CategoryServiceModel.class);
        categoryServiceModel.setUser(principal.getUsername());
        categoryService.addCategory(categoryServiceModel);


        return "redirect:/categories/all";
    }

    @GetMapping("/all")
    public String allCategory(Model model) {

        List<CategoryViewModel> categories = this.categoryService
                .findAllCategories()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());

      //  modelAndView.addObject("categories", categories);
        model.addAttribute("categories", categories);
        //много внимаваме как сче го кръстили в html иначе няма да ни го мапни


        return "category/category-all";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable String id, Model model){

        CategoryEditViewModel categoryEditViewModel = this.modelMapper
                .map(this.categoryService.findCategoryById(id), CategoryEditViewModel.class);

        model.addAttribute("category", categoryEditViewModel);

        return "category/category-edit";

    }

    @PostMapping("/edit/{id}")
    public String newEditCategory(@PathVariable String id, CategoryAddBindingModel model){

        this.categoryService.editCategory(id, this.modelMapper.map(model, CategoryServiceModel.class));

        return "redirect:/categories/all";

    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id, Model model){

        CategoryDeleteViewModel categoryDeleteViewModel =
                this.modelMapper
                        .map(this.categoryService.findCategoryById(id), CategoryDeleteViewModel.class);
        model.addAttribute("category", categoryDeleteViewModel);

        return "category/category-delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id){
        this.categoryService.deleteCategory(id);

        return "redirect:/categories/all";
    }


}
