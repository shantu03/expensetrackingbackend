package expense.expensetracking.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

// Sent TO the client
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {

    private String username;
    private List<ExpenseItem> expenses;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExpenseItem {
        private Integer expenseId;       // needed for delete & edit
        private String description;
        private Double amount;
        private String platform;
        private LocalDate transactionDate;
    }
}