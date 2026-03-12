package expense.expensetracking.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.PropertyNamingStrategy;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
    private String username;
    private String password;
}
