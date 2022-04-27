import { Component, OnInit } from '@angular/core';
import {Session} from "../_datatypes/session";
import {SessionService} from "../_services/session.service";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.css']
})
export class SessionsComponent implements OnInit {

  sessions: Session[] = [];
  displayedColumns: string[] = ['id', 'name', "creator", "createdDate"];
  ofCreator: boolean;
  pageNumber: number;
  pageSize: number;
  maxPage: number;
  isLoggedIn = false;
  userProfile: KeycloakProfile | null = null;

  constructor(private sessionService : SessionService,
              private readonly keycloak : KeycloakService,
              private readonly translate: TranslateService) {
    this.ofCreator = false;
    this.pageNumber = 0;
    this.pageSize = 20;
    this.maxPage = 0;
  }

  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
      this.refresh();
    }
  }

  getSessions(pageNum: number, pageSize: number, ofCreator : boolean, creatorName? : string): void {
    this.sessionService.getSessions(pageNum, pageSize, ofCreator, creatorName)
      .subscribe(sessions => this.sessions = sessions);
  }

  refresh() {
    if (this.userProfile !== undefined && this.userProfile !== null && this.userProfile.username !== undefined) {
      this.getSessions(this.pageNumber, this.pageSize, this.ofCreator, this.userProfile?.username);
    } else {
      this.getSessions(this.pageNumber, this.pageSize, this.ofCreator);
    }
    this.maxPage = Math.ceil(this.sessions.length / this.pageSize);
  }

}
