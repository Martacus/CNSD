package com.mart.SpringBoot;

import com.mart.SpringBoot.models.AccountOld;
import com.mart.SpringBoot.models.RekeningOld;
import com.mart.SpringBoot.services.RekeningService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
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
