import {Injectable} from '@angular/core';
import {Session} from "../_datatypes/session";
import {catchError, Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private sessionsUrl = 'http://INSERT YOUR IP HERE:8090/sessions';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private readonly alertService: AlertService
  ) { }

  getSessions(pageNum: number, pageSize: number, ofCreator : boolean, creatorName? : string): Observable<Session[]> {
    let getSessionsUrl;
    if (ofCreator && (creatorName !== undefined)) {
      getSessionsUrl = `${this.sessionsUrl}?page=${pageNum}&size=${pageSize}&creator=${creatorName}`;
    } else {
      getSessionsUrl = `${this.sessionsUrl}?page=${pageNum}&size=${pageSize}`;
    }
    return this.http.get<Session[]>(getSessionsUrl).pipe(
      catchError(this.handleError<Session[]>())
    );
  }

  getSession(id: string): Observable<Session> {
    const url = `${this.sessionsUrl}/${id}`;
    return this.http.get<Session>(url);
  }

  addSession(session: Session): Observable<any> {
    return this.http.post<any>(this.sessionsUrl, session, this.httpOptions).pipe(
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
