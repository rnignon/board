package goorm.board.entity;

import goorm.board.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE comment SET DELETE_STATUS = 'DELETE' WHERE id = ?")
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    public Comment(String content) {
        this.content = content;
        this.deleteStatus = DeleteStatus.ACTIVE;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDeleteStatus(DeleteStatus deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void update(String content) {
        this.content = content;
    }
}
