package expense.expensetracking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<ExpenseModel> expenses;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}