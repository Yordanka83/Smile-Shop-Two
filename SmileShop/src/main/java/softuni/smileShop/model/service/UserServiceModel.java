package softuni.smileShop.model.service;

import java.util.List;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private String email;

    private List<RoleServiceModel> authorities;

    public UserServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public UserServiceModel setAuthorities(List<RoleServiceModel> authorities) {
        this.authorities = authorities;
        return this;
    }
}
