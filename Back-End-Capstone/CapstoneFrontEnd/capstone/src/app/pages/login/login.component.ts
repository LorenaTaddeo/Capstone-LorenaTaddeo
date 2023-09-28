import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { LoginData } from '../auth/interfaces/login-data';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  protected isSubmitted=false;

  protected loginForm = new FormGroup({
    username: new FormControl("", Validators.required),
    password: new FormControl("", [Validators.required]),
  });

  constructor(
    private authSvc: AuthService,
    private router: Router
  ){
    console.log('sono nel login')
  }

  onSubmit(){
    this.isSubmitted=true;
    if(this.loginForm.valid){
    this.login();
    } 
  }

  private login(){
    this.authSvc.login(this.loginForm.getRawValue() as LoginData)
    .subscribe(accessData => {
    this.router.navigate(['/book']);
    })
  }
}
