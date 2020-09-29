import { Component, OnInit } from '@angular/core';
import {AuthService} from '../services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  alert: String = null;
  username: String;
  password: String;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  login(): void{
    console.log(this.username);
    console.log(this.password);
    this.authService.login(this.username, this.password).then((res)=>{
      console.log(res);
    }).catch((err)=>{
      this.alert = err;
    });
  }

}
