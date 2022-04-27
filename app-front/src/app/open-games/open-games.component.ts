import { Component, OnInit } from '@angular/core';
import {OpenGameQuery} from "../_datatypes/open-game-query";
import {OpenGameService} from "../_services/open-game.service";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-open-games',
  templateUrl: './open-games.component.html',
  styleUrls: ['./open-games.component.css']
})
export class OpenGamesComponent implements OnInit {
  openGames: OpenGameQuery[] = [];
  displayedColumns: string[] = ['id', 'name', 'creator', 'createdDate', 'isFinished']
  areOpen: boolean;
  areOfParticipant: boolean;
  pageNumber: number;
  pageSize: number;
  maxPage: number;
  isLoggedIn = false;
  userProfile: KeycloakProfile | null = null;

  constructor(private openGameService : OpenGameService,
              private readonly keycloak : KeycloakService,
              private readonly translate: TranslateService) {
    this.areOpen = false;
    this.areOfParticipant = false;
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

  getOpenGames(pageNum: number, pageSize: number, areOpen : boolean, ofParticipant : boolean, participantName? : string): void {
    this.openGameService.getOpenGames(pageNum, pageSize, areOpen, ofParticipant, participantName)
      .subscribe(openGames => this.openGames = openGames);
  }

  refresh() {
    if (this.userProfile !== undefined && this.userProfile && this.userProfile.username !== undefined) {
      this.getOpenGames(this.pageNumber, this.pageSize, this.areOpen, this.areOfParticipant, this.userProfile.username);
    } else {
      this.getOpenGames(this.pageNumber, this.pageSize, this.areOpen, this.areOfParticipant);
    }
    this.maxPage = Math.ceil(this.openGames.length / this.pageSize);
  }
}
