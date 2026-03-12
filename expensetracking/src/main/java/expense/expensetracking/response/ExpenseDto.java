package expense.expensetracking.response;


import expense.expensetracking.model.ExpenseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

private List<ExpenseModel> expenses;
private String username;
}
