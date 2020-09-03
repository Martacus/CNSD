package com.mart.SpringBoot;

import com.mart.SpringBoot.models.Account;
import com.mart.SpringBoot.models.Rekening;
import com.mart.SpringBoot.services.RekeningService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RekeningController {

    private final RekeningService service;

    public RekeningController(RekeningService service) {
        this.service = service;
    }

    @GetMapping("/rekening")
    public List<Rekening> getAllRekening(){
        return service.getAllRekeningen();
    }

    @GetMapping("/rekening/{id}")
    public Rekening getRekening(@PathVariable String id){
        return service.getById(id);
    }

    @PutMapping("/rekening/{id}/togglelock")
    public Rekening toggleRekening(@PathVariable String id){
        service.lockRekening(id);
        return service.getById(id);
    }

    @PostMapping("/rekening")
    public Account postRekening(@Valid @RequestBody Account account){
        service.addNewAccount(account, new Rekening());
        return account;
    }

    @DeleteMapping("/rekening/{id}")
    public Rekening deleteRekening(@PathVariable String id){
        return service.removeRekening(id);
    }

    @PostMapping("/rekening/{id}/newowner")
    public Account postNewOwner(@RequestBody Account account, @PathVariable String id){
        service.addAccountToRekening(account, id);
        return account;
    }

    @DeleteMapping("/rekening/{id}/newowner")
    public Account deleteOwner(@RequestBody Account account, @PathVariable String id){
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
