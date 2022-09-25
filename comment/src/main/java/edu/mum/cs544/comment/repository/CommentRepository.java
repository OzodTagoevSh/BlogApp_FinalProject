package edu.mum.cs544.comment.repository;

import edu.mum.cs544.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByUserId(Integer userId);
    List<Comment> findAllByPostId(Integer postId);
}
