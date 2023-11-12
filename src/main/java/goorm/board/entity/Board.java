package goorm.board.entity;

import goorm.board.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// Spring Data JPA에서 delete 메소드를 호출할 때에 삭제 대신 미리 정의된 쿼리를 호출하도록 설정
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE id = ?")
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
        this.deleteStatus = DeleteStatus.ACTIVE;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDeleteStatus(DeleteStatus deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
