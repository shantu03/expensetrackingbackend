package expense.expensetracking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="expanses")
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;

    @Column(name="user_id",nullable = false)
    private Integer userId;
    private String description;

    @Column(nullable = false)
    private Double amount;

    private String platform="NA";
    @Column(nullable = false)
    private LocalDate transactionDate;
}
