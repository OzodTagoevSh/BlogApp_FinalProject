package edu.mum.cs544.post.repository;

import edu.mum.cs544.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
