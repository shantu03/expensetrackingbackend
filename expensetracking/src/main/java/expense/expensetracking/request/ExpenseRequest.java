package expense.expensetracking.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {

    private String username;      // temporary, remove once JWT is added
    private Integer expenseId;    // null on add, required on edit
    private String description;
    private Double amount;
    private String platform;
    private LocalDate transactionDate;
}
