import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from './ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class SigninService {
  private signin = "https://api.dev.ganeyi.baamtuservices.com/api/signin"
  access_token: any;
  constructor(private httpclient: HttpClient) { }

  signinUser():Observable<ApiResponse> {
    const body = {login:"fatoukine.ndiaye@baamtu.com", password:"Kenza0892"};
    return this.httpclient.post<any>(this.signin,body);
  }

  getToken(){
        
        this.signinUser().subscribe(data => {
        this.access_token = data.access_token;
        localStorage.setItem("access_token", this.access_token);
      
    })
  
  }
}
