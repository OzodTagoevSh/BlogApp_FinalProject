package edu.mum.cs544.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private boolean success;
    private String message;
    private Object data;


    public UserResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.message = "Request completed!";
    }
}
