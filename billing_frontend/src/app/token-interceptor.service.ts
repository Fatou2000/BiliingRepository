import { Injectable,Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FactureService } from './facture.service';
import { SigninService } from './signin.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{
  urlsToNotUse: Array<string>;

  constructor(private signinservice : SigninService ) { 
    this.urlsToNotUse= [
      'https://api.dev.ganeyi.baamtuservices.com/',  
    ];
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


    this.signinservice.signinUser();
    this.signinservice.getToken();
    let access_token = localStorage.getItem("access_token");
    if (this.isValidRequestForInterceptor(req.url)) {
      const cloned = req.clone({
        setHeaders:{
          Authorization :'Bearer ' + access_token}
      });
      return next.handle(cloned);
    }
      return next.handle(req);
  }

  private isValidRequestForInterceptor(requestUrl: string): boolean {
    let positionIndicator: string = 'api/';
    let position = requestUrl.indexOf(positionIndicator,requestUrl.indexOf(requestUrl)+1);
    if (position > 0) {
      let signin: string = requestUrl.substring(position + positionIndicator.length);
      let notAuthorizedRoute: string = "signin";
      if (signin === notAuthorizedRoute) {
        return false;
      } 
    }
    return true;
  }
}
