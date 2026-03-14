package expense.expensetracking.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="expenses")
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;

    @Column(name="user_id",nullable = false)
    private Integer userId;
    private String description;

    @Column(nullable = false)
    private Double amount;

    private String platform;
    @Column(nullable = false)
    private LocalDate transactionDate;
}
