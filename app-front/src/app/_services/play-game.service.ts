import { Injectable } from '@angular/core';
import {catchError, Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PlayedGame} from "../_datatypes/played-game";
import {GameResults} from "../_datatypes/game-results";
import {SessionDays} from "../_datatypes/session-days";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class PlayGameService {

  private gamesUrl = 'http://INSERT YOUR IP HERE:8090/open-games';
  private sessionsUrl = 'http://INSERT YOUR IP HERE:8090/sessions';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private readonly alertService: AlertService) { }

  addPlayedGame(id: string, playedGame: PlayedGame): Observable<any> {
    const url = `${this.gamesUrl}/${id}/results`;
    return this.http.post<any>(url, playedGame, this.httpOptions).pipe(
      catchError(this.handleError<any>())
    );
  }

  addMockGame(id: string): Observable<any> {
    const url = `${this.gamesUrl}/${id}/mockResults`;
    return this.http.post<any>(url, this.httpOptions).pipe(
      catchError(this.handleError<any>())
    );
  }

  getGameResults(id: string): Observable<GameResults> {
    const url = `${this.gamesUrl}/${id}/results`;
    return this.http.get<GameResults>(url).pipe(
      catchError(this.handleError<GameResults>())
    );
  }

  getSessionDays(id: number): Observable<SessionDays> {
    const url = `${this.sessionsUrl}/${id}/days`;
    return this.http.get<SessionDays>(url);
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      this.alertService.error(error.error.message);
      return of(result as T);
    };
  }
}
