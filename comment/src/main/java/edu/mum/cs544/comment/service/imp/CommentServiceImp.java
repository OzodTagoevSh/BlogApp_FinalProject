package edu.mum.cs544.comment.service.imp;

import edu.mum.cs544.comment.dto.CommentDto;
import edu.mum.cs544.comment.dto.CommentRequest;
import edu.mum.cs544.comment.dto.CommentResponse;
import edu.mum.cs544.comment.entity.Comment;
import edu.mum.cs544.comment.repository.CommentRepository;
import edu.mum.cs544.comment.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentResponse getAll() {
        return new CommentResponse(commentRepository.findAll(), true, "Success");
    }

    @Override
    public CommentResponse addComment(Integer userId, CommentRequest commentRequest) {
        LocalDate date = LocalDate.now();
        Comment comment = commentRepository.save(new Comment(commentRequest.getComment(), userId, commentRequest.getPostId(), date, date));
        return new CommentResponse(modelMapper.map(comment, CommentDto.class), true, "Comment saved successfully!");
    }

    @Override
    public CommentResponse deleteById(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return new CommentResponse(null, false, "Comment not Found");
        }
        commentRepository.delete(comment);
        return new CommentResponse(null, true, "Comment deleted successfully!");
    }

    @Override
    public CommentResponse updateComment(Integer id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return new CommentResponse(null, false, "Comment Not Found!");
        }
        comment.setComment(commentRequest.getComment());
        comment.setUpdatedAt(LocalDate.now());
        comment.setPostId(commentRequest.getPostId());
        commentRepository.save(comment);
        return new CommentResponse(null, false, "Updated successfully!");
    }

    @Override
    public CommentResponse getById(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return new CommentResponse(null, false, "Comment not found!");
        }
        return new CommentResponse(modelMapper.map(comment, CommentDto.class), true, "Success");
    }

    @Override
    public CommentResponse commentsByUser(Integer userId) {
        return new CommentResponse(commentRepository.findAllByUserId(userId), true, "Success");
    }

    @Override
    public CommentResponse commentsByPostId(Integer postId) {
        return new CommentResponse(commentRepository.findAllByPostId(postId), true, "Success");
    }
}
