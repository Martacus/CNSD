package com.mart.SpringBoot;

import com.mart.SpringBoot.models.Account;
import com.mart.SpringBoot.models.AccountOld;
import com.mart.SpringBoot.models.RekeningOld;
import com.mart.SpringBoot.models.User;
import com.mart.SpringBoot.services.AccountRepository;
import com.mart.SpringBoot.services.RekeningService;
import com.mart.SpringBoot.services.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RekeningController {

    private final RekeningService service;

    @PersistenceContext
    EntityManager entityManager;


    public RekeningController(RekeningService service, AccountRepository accounRepository, UserRepository userRepository) {
        this.service = service;
        accounRepository.save(new Account());
        userRepository.save(new User());
    }

    @GetMapping("/rekening")
    public List<RekeningOld> getAllRekening(){
        return service.getAllRekeningen();
    }

    @GetMapping("/rekening/{id}")
    public RekeningOld getRekening(@PathVariable String id){
        return service.getById(id);
    }

    @PutMapping("/rekening/{id}/togglelock")
    public RekeningOld toggleRekening(@PathVariable String id){
        service.lockRekening(id);
        return service.getById(id);
    }

    @PostMapping("/rekening")
    public AccountOld postRekening(@Valid @RequestBody AccountOld account){
        Account newAccount = new Account();
        entityManager.getTransaction().begin();
        entityManager.persist(newAccount);
        entityManager.getTransaction().commit();
        service.addNewAccount(account, new RekeningOld());
        return account;
    }

    @DeleteMapping("/rekening/{id}")
    public RekeningOld deleteRekening(@PathVariable String id){
        return service.removeRekening(id);
    }

    @PostMapping("/rekening/{id}/newowner")
    public AccountOld postNewOwner(@RequestBody AccountOld account, @PathVariable String id){
        service.addAccountToRekening(account, id);
        return account;
    }

    @DeleteMapping("/rekening/{id}/newowner")
    public AccountOld deleteOwner(@RequestBody AccountOld account, @PathVariable String id){
        service.removeAccountFromRekening(account, id);
        return account;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
