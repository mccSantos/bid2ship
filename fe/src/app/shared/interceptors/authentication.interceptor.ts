import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private authService: AuthenticationService, private router: Router) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    var newRequest = request;
    if (this.authService.isAuthenticated()) {
      const jwt = this.authService.getCurrentToken();

      newRequest = request.clone({
        setHeaders: { authorization: `Bearer ${jwt}` },
      });
    }

    return next.handle(newRequest).pipe(
      tap({
        next: (event) => {
          if (event instanceof HttpResponse) {
            if (event.status == 401) {
              this.authService.removeToken();
              this.router.navigateByUrl("/login")
            }
          }
          return event;
        },
        error: (error) => {
          if(error.status === 401) {
            this.authService.removeToken();
              this.router.navigateByUrl("/login")
          }
          else if(error.status === 404) {
            console.log("Page not found!!!")
            //alert('Page Not Found!')
          }
        }
      })
    );
  }
}
