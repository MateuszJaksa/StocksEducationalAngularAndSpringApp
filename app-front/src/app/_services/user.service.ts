import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of} from 'rxjs';
import {RegisteringUser} from "../_datatypes/registering-user";
import {Username} from "../_datatypes/username";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private registerUrl = 'http://INSERT YOUR IP HERE:8090/register';
  private getUsersUrl = 'http://INSERT YOUR IP HERE:8090/users';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private readonly alertService: AlertService
  ) { }

  addUser(user: RegisteringUser): Observable<any> {
    return this.http.post<any>(this.registerUrl, user, this.httpOptions).pipe(
      catchError(this.handleError<any>())
    );
  }

  getUsernames(): Observable<Username[]> {
    return this.http.get<Username[]>(this.getUsersUrl);
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      this.alertService.error(error.error.message);
      return of(result as T);
    };
  }
}
