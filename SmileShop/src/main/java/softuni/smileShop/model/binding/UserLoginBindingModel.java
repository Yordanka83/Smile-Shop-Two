package softuni.smileShop.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {
    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @NotBlank(message = "Username cannot be empty str")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank(message = "password cannot empty str")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
