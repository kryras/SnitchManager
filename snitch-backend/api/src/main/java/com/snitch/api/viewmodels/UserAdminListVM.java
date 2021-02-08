package com.snitch.api.viewmodels;

import com.snitch.entities.model.User;
import lombok.Data;

@Data
public class UserAdminListVM {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer roleId;

    public UserAdminListVM(User user){

        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roleId = user.getRoles().stream().findFirst().get().getId();
    }
}
