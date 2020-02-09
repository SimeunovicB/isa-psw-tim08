import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private cookieService: CookieService) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.cookieService.get('token') !== null) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.cookieService.get('token').substr(0,this.cookieService.get('token').length)}`
        }
      });
    }
    return next.handle(request);
  }
}