package softuni.smileShop.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryAddBindingModel {
    private String name;

    public CategoryAddBindingModel() {
    }

    @NotBlank(message = "Name cannot be empty str")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
