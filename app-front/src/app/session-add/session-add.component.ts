import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {ActivatedRoute} from "@angular/router";
import {SessionService} from "../_services/session.service";
import {Session} from "../_datatypes/session";
import {CompaniesService} from "../_services/companies.service";
import {Company} from "../_datatypes/company";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-session-add',
  templateUrl: './session-add.component.html',
  styleUrls: ['./session-add.component.css']
})
export class SessionAddComponent implements OnInit {

  session: Session | undefined;
  notAddedCompanies: Company[] = [];
  addedCompanies: Company[] = [];

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private companiesService: CompaniesService,
    private location: Location,
    private readonly translate: TranslateService) {}

    ngOnInit(): void {
    this.session = {} as Session;
    this.getAllCompanies();
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.session !== null && this.session !== undefined) {
      this.session.companies = this.addedCompanies;
      this.sessionService.addSession(this.session)
        .subscribe(() => this.goBack());
    }
  }

  getAllCompanies(): void {
    this.companiesService.getAllCompanies()
      .subscribe(companies => this.notAddedCompanies = companies);
  }

  addCompany(company: Company): void {
    this.addedCompanies.push(company);
    this.notAddedCompanies.splice(this.notAddedCompanies.indexOf(company), 1);
  }

  removeCompany(company: Company): void {
    this.notAddedCompanies.push(company);
    this.addedCompanies.splice(this.addedCompanies.indexOf(company), 1);
  }
}
