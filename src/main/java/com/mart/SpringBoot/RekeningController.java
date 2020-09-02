package com.mart.SpringBoot;

import com.mart.SpringBoot.models.Account;
import com.mart.SpringBoot.models.Rekening;
import com.mart.SpringBoot.services.RekeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Account postRekening(@RequestBody Account account){
        service.addNewAccount(account, new Rekening());
        return account;
    }

    @DeleteMapping("/rekening/{id}")
    public Rekening deleteRekening(@PathVariable String id){
        return service.removeRekening(id);
    }
}
