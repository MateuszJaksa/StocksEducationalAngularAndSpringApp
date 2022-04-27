import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of} from "rxjs";
import {UserBest, UserStats} from "../_datatypes/user-stats";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class UserStatsService {

  private statsUrl = 'http://INSERT YOUR IP HERE:8090/user-stats';
  private bestUrl = 'http://INSERT YOUR IP HERE:8090/user-best';

  constructor(
    private http: HttpClient,
    private readonly alertService: AlertService) { }

  getUserStats(): Observable<UserStats> {
    return this.http.get<UserStats>(this.statsUrl).pipe(
      catchError(this.handleError<UserStats>())
    );
  }

  getUserBests(): Observable<UserBest[]> {
    return this.http.get<UserBest[]>(this.bestUrl).pipe(
      catchError(this.handleError<UserBest[]>())
    );
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      this.alertService.error(error.error.message);
      return of(result as T);
    };
  }
}
