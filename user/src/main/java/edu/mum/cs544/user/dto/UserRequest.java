package edu.mum.cs544.user.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull
    private String username;
    private String firstname;
    private String lastname;
    @NotNull
    private String password;
    @NotNull
    private String roleName;
}
