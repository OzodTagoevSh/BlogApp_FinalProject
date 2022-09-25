package edu.mum.cs544.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private boolean success;
    private String message;
    private Object data;

    public CommentResponse(Object data, boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
