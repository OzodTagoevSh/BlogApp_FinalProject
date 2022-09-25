package edu.mum.cs544.post.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    @Column(unique = true)
    @NotNull
    private String title;
    @NotNull
    private String content;
}
