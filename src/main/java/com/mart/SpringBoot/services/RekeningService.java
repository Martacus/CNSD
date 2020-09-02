package com.mart.SpringBoot.services;

import com.mart.SpringBoot.models.Account;
import com.mart.SpringBoot.models.Rekening;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RekeningService {

    public HashMap<Account, List<Rekening>> accountsRekeningen;
    public HashMap<Rekening, List<Account>> rekeningAccounts;

    public RekeningService(){
        this.accountsRekeningen = new HashMap<>();
        this.rekeningAccounts = new HashMap<>();
    }

    public void addNewAccount(Account account, Rekening rekening){
        Optional<Account> optionalAccount = accountsRekeningen.keySet().stream()
                .filter(rekenings -> rekenings.getId().equals(account.getId()))
                .findFirst();
        if(optionalAccount.isPresent()){
            List<Rekening> rekeningen = accountsRekeningen.get(optionalAccount.get());
            rekeningen.add(rekening);
            addRekening(rekening, optionalAccount.get());
            return;
        } else {
            List<Rekening> rekeningList = new ArrayList<>();
            rekeningList.add(rekening);
            accountsRekeningen.put(account, rekeningList);
            addRekening(rekening, account);
        }
    }

    public void addRekening(Rekening rekening, Account account){
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        rekeningAccounts.put(rekening, accounts);
    }

    public Rekening getById(String id){
        Optional<Rekening> optionalRekening = getRekening(id);

        return optionalRekening.orElse(null);
    }

    public Rekening removeRekening(String id){
        Optional<Rekening> optionalRekening = getRekening(id);
        if(optionalRekening.isPresent()){
            List<Account> accounts = rekeningAccounts.remove(optionalRekening.get());
            for(Account account : accounts){
                List<Rekening> rekeningen = accountsRekeningen.get(account);
                rekeningen.remove(optionalRekening.get());
            }

        }

        return optionalRekening.orElse(null);
    }

    public void lockRekening(String id){
        Optional<Rekening> optionalRekening = getRekening(id);

        optionalRekening.ifPresent(rekening -> rekening.setLocked(!rekening.isLocked()));
    }

    public List<Rekening> getAllRekeningen() {
        List<Rekening> rekeningen = rekeningAccounts.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return rekeningen;
    }

    public Optional<Rekening> getRekening(String uuid){
        return rekeningAccounts.keySet().stream()
                .filter(rekening -> rekening.getId().equals(UUID.fromString(uuid)))
                .findFirst();
    }
//
//    public void addSaldo(String id, int saldo){
//        for (Rekening r : rekeningen){
//            if(r.getId().toString().equals(id)){
//                r.setSaldo(r.getSaldo() + saldo);
//            }
//        }
//    }

}
