import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ChartData} from "../_datatypes/chart-data";

@Injectable({
  providedIn: 'root'
})
export class ChartService {

  private sessionsUrl = 'http://INSERT YOUR IP HERE:8090/sessions';

  constructor(
    private http: HttpClient) { }

  getChartData(id: number): Observable<ChartData[]> {
    const url = `${this.sessionsUrl}/${id}/chart-data`;
    return this.http.get<ChartData[]>(url);
  }
}
