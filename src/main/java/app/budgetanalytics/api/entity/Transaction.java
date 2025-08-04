package app.budgetanalytics.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private long amount;

    private Date date;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
