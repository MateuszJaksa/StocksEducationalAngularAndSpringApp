import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of} from "rxjs";
import {OpenGameQuery} from "../_datatypes/open-game-query";
import {OpenGameCommand} from "../_datatypes/open-game-command";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class OpenGameService {

  private gamesUrl = 'http://INSERT YOUR IP HERE:8090/open-games';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private readonly alertService: AlertService) { }

  getOpenGames(pageNum: number, pageSize: number, areOpen : boolean, ofParticipant : boolean, participantName? : string): Observable<OpenGameQuery[]> {
    let getGamesUrl;
    if (ofParticipant && (participantName !== undefined)) {
      getGamesUrl = `${this.gamesUrl}?page=${pageNum}&size=${pageSize}&areOpen=${areOpen}&participant=${participantName}`;
    } else {
      getGamesUrl = `${this.gamesUrl}?page=${pageNum}&size=${pageSize}&areOpen=${areOpen}`;
    }
    return this.http.get<OpenGameQuery[]>(getGamesUrl).pipe(
      catchError(this.handleError<OpenGameQuery[]>())
    );
  }

  getOpenGameById(id: string): Observable<OpenGameQuery> {
    const url = `${this.gamesUrl}/${id}`;
    return this.http.get<OpenGameQuery>(url);
  }

  addOpenGame(openGame: OpenGameCommand): Observable<any> {
    return this.http.post<any>(this.gamesUrl, openGame, this.httpOptions).pipe(
      catchError(this.handleError<any>())
    );
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      this.alertService.error(error.error.message);
      return of(result as T);
    };
  }
}
