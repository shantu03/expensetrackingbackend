package expense.expensetracking.controller;

import expense.expensetracking.Dto.UserDto;
import expense.expensetracking.request.ExpenseRequest;
import expense.expensetracking.response.ExpenseResponse;
import expense.expensetracking.service.JwtService;
import expense.expensetracking.service.UserExpenseService;
import expense.expensetracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserExpenseService userExpenseService;

    @Autowired
    private JwtService jwtService;



    @PostMapping("/login")
    public ResponseEntity<?> getUserData(@RequestBody UserDto user) {
        try {
            ExpenseResponse expense = userService.getUserData(user);

            String token=jwtService.generateToken(user.getUsername());
            return new ResponseEntity<>(Map.of(
                    "token",token,
                    "data",expense
            ), HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("4"))
                return new ResponseEntity<>(e.getMessage().substring(2), HttpStatus.NOT_FOUND);
            if (e.getMessage().contains("5"))
                return new ResponseEntity<>(e.getMessage().substring(2), HttpStatus.UNAUTHORIZED);

            return new ResponseEntity<>("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody UserDto user)
    {
        try{
            Boolean isUserAlreadyPresent=userService.signupUser(user);

            if(Boolean.FALSE.equals(isUserAlreadyPresent))
                return new ResponseEntity<>("Username already Exists",HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>("User Created !",HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity<>("server error in signup",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addExpense")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequest expense)
    {
//        we are assuming that user exist
        return new ResponseEntity<>(userExpenseService.addExpanse(expense),HttpStatus.OK);

    }



}
