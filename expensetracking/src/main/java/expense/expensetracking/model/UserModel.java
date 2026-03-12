package expense.expensetracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    @lombok.ToString.Exclude
    private List<ExpenseModel> expenses;
}