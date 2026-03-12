package expense.expensetracking.controller;

import expense.expensetracking.Dto.UserDto;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


   @PostMapping("/login")
    public ResponseEntity<?> getUserData(@RequestBody UserDto user){
       try{

        UserModel user1 =userService.getUserData(user);
        return new ResponseEntity<>(user1.getExpenses(),HttpStatus.OK);
       }catch (Exception e )
       {
           if(e.getMessage().contains("4"))
               return new ResponseEntity<>(e.getMessage().substring(2),HttpStatus.NOT_FOUND);
           if(e.getMessage().contains("5"))
               return new ResponseEntity<>(e.getMessage().substring(2),HttpStatus.UNAUTHORIZED);

           return new ResponseEntity<>("server login error",HttpStatus.OK);
       }


   }


}
