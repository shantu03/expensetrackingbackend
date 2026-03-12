package expense.expensetracking.model;

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
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @lombok.ToString.Exclude
    private UserModel user;

    private String description;
    private Double amount;
    private String platform;
    private LocalDate transactionDate;
}
