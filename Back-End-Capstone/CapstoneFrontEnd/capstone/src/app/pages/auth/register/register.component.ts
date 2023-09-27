import { Component } from '@angular/core';
import { RegisterData } from '../interfaces/register-data';
import { AuthService } from '../auth.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})

export class RegisterComponent {

  protected isSubmitted=false;

  protected registerForm = new FormGroup({
    username: new FormControl("", Validators.required),
    name: new FormControl("", Validators.required),
    lastname: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.required,Validators.email]),
    password: new FormControl("", [Validators.required]),
  });

  constructor(
    private authSvc: AuthService,
    private router: Router
  ){}

  onSubmit(){
    this.isSubmitted=true;
    if(this.registerForm.valid){
    this.register();
    } 
  }

  private register(){
    this.authSvc.signUp(this.registerForm.getRawValue() as RegisterData)
    .subscribe(accessData => {
    this.router.navigate(['/login']);
    })
  }
}
