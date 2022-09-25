package edu.mum.cs544.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private boolean success;
    private String message;
    private Object data;

    public PostResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.message = "Request completed!";
    }
}
