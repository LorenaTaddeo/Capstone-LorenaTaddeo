import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable, map, switchMap, tap } from 'rxjs';
import { AccessData } from './interfaces/access-data';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginData } from './interfaces/login-data';
import { RegisterData } from './interfaces/register-data';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  jwtHelper:JwtHelperService = new JwtHelperService();
  apiUrl = 'http://127.0.0.1:8080/api/auth';

  private authSubject = new BehaviorSubject<null | AccessData>(null);

  user$ = this.authSubject.asObservable();
  isLoggedIn$ = this.user$.pipe(map(dato => Boolean(dato)));
  private authToken? :string;

  authLogoutTimer:any;

  constructor(
    private http: HttpClient,
  ) {
    const accessDataString= localStorage.getItem('accessData')
    if(accessDataString){
      const accessData: AccessData= JSON.parse(accessDataString || '');
      if(accessData){
        this.authToken= `${accessData.tokenType} ${accessData.accessToken}`;
        this.authSubject.next(accessData)
      }
    }
   
   }

getAuthToken():string {
  return this.authToken || ''
}

logout(): void{
  localStorage.removeItem('accessData');
  this.authSubject.next(null);
}



  login(data:LoginData): Observable<AccessData>{
    let accessData={} as AccessData
    return this.http.post<Omit<AccessData, 'id'>>(this.apiUrl + '/login', data)
    .pipe(tap(data =>{
      accessData=data;
      this.authSubject.next(data);
      const expDate = this.jwtHelper
      .getTokenExpirationDate(data.accessToken) as Date;
      this.authToken= `${data.tokenType} ${data.accessToken}`;
     
    }),
    switchMap(()=>{
      return this.http.get(`http://127.0.0.1:8080/api/users/username/${data.username}`)
    }),
    map((userAdditionalData: any)=>{
      accessData.id=userAdditionalData.id;
      return userAdditionalData;
    }),
    tap((data: AccessData)=>{
      localStorage.setItem('accessData', JSON.stringify(accessData));
    })
      
    )
  }

  signUp(data:RegisterData){
    return this.http.post(this.apiUrl + '/register', data,{responseType: 'text'});
  }

}
