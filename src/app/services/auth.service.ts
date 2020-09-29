import { Injectable } from '@angular/core';
import { Account } from '../model/account';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  accounts: Account[] = [
    { username: "mart", password: '1' },
    { username: "riang", password: '2' },
    { username: "kek", password: '3' },
    { username: "top", password: '4' },
  ];

  constructor() {}

  login(username: String, password: String): Promise<any>{
    return new Promise((resolve, reject) => {
      this.accounts.forEach(function (e) {
        if (e.username === username && e.password === password) {
          resolve(e);
        }
      });

      reject("Login Parameters are wrong");
    })
   
  }
}
