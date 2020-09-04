package com.mart.SpringBoot.services;

import com.mart.SpringBoot.models.AccountOld;
import com.mart.SpringBoot.models.RekeningOld;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RekeningService {

    public HashMap<AccountOld, List<RekeningOld>> accountsRekeningen;
    public HashMap<RekeningOld, List<AccountOld>> rekeningAccounts;

    public RekeningService(){
        this.accountsRekeningen = new HashMap<>();
        this.rekeningAccounts = new HashMap<>();
    }

    public void addNewAccount(AccountOld account, RekeningOld rekening){
        Optional<AccountOld> optionalAccount = accountsRekeningen.keySet().stream()
                .filter(rekenings -> rekenings.getId().equals(account.getId()))
                .findFirst();
        if(optionalAccount.isPresent()){
            List<RekeningOld> rekeningen = accountsRekeningen.get(optionalAccount.get());
            rekeningen.add(rekening);
            addRekening(rekening, optionalAccount.get());
        } else {
            List<RekeningOld> rekeningList = new ArrayList<>();
            rekeningList.add(rekening);
            accountsRekeningen.put(account, rekeningList);
            addRekening(rekening, account);
        }
    }

    public void addAccountToRekening(AccountOld account, String rekeningId){
        Optional<RekeningOld> optionalRekening = getRekening(rekeningId);
        optionalRekening.ifPresent(rekening -> addNewAccount(account, rekening));
    }

    public void removeAccountFromRekening(AccountOld account, String rekeningId){
        Optional<AccountOld> optionalAccount = accountsRekeningen.keySet().stream()
                .filter(rekenings -> rekenings.getId().equals(account.getId()))
                .findFirst();

        if(optionalAccount.isPresent()){
            List<RekeningOld> accountRekeningen = this.accountsRekeningen.get(optionalAccount.get());
            Optional<RekeningOld> optionalRekening = getRekening(rekeningId);
            optionalRekening.ifPresent(accountRekeningen::remove);
            this.accountsRekeningen.put(optionalAccount.get(), accountRekeningen);
        }


    }

    public void addRekening(RekeningOld rekening, AccountOld account){
        List<AccountOld> accounts = new ArrayList<>();
        accounts.add(account);
        rekeningAccounts.put(rekening, accounts);
    }

    public RekeningOld getById(String id){
        Optional<RekeningOld> optionalRekening = getRekening(id);

        return optionalRekening.orElse(null);
    }

    public RekeningOld removeRekening(String id){
        Optional<RekeningOld> optionalRekening = getRekening(id);
        if(optionalRekening.isPresent()){
            List<AccountOld> accounts = rekeningAccounts.remove(optionalRekening.get());
            for(AccountOld account : accounts){
                List<RekeningOld> rekeningen = accountsRekeningen.get(account);
                rekeningen.remove(optionalRekening.get());
            }

        }

        return optionalRekening.orElse(null);
    }

    public void lockRekening(String id){
        Optional<RekeningOld> optionalRekening = getRekening(id);

        optionalRekening.ifPresent(rekening -> rekening.setLocked(!rekening.isLocked()));
    }

    public List<RekeningOld> getAllRekeningen() {
        List<RekeningOld> rekeningen = rekeningAccounts.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return rekeningen;
    }

    public Optional<RekeningOld> getRekening(String uuid){
        return rekeningAccounts.keySet().stream()
                .filter(rekening -> rekening.getId().equals(UUID.fromString(uuid)))
                .findFirst();
    }

}
