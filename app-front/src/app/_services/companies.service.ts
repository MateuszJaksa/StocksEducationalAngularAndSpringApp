import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Company} from "../_datatypes/company";

@Injectable({
  providedIn: 'root'
})
export class CompaniesService {

  private apiUrl = 'http://INSERT YOUR IP HERE:8090/companies';

  constructor(
    private http: HttpClient) { }

  getAllCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(this.apiUrl);
  }
}
