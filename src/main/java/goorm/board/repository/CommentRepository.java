package goorm.board.repository;

import goorm.board.DeleteStatus;
import goorm.board.entity.Board;
import goorm.board.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByDeleteStatus(DeleteStatus deleteStatus, Pageable pageable);
}
