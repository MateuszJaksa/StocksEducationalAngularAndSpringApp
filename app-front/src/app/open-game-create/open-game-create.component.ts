import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {OpenGameService} from "../_services/open-game.service";
import {OpenGameCommand} from "../_datatypes/open-game-command";
import {UserService} from "../_services/user.service";
import {Username} from "../_datatypes/username";
import {SessionService} from "../_services/session.service";
import {Session} from "../_datatypes/session";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-open-game-create',
  templateUrl: './open-game-create.component.html',
  styleUrls: ['./open-game-create.component.css']
})
export class OpenGameCreateComponent implements OnInit {

  openGame: OpenGameCommand | undefined;
  notAddedUsernames: Username[] = [];
  addedUsernames: Username[] = [];
  sessions: Session[] = [];

  constructor(
    private route: ActivatedRoute,
    private openGameService: OpenGameService,
    private sessionService : SessionService,
    private userService: UserService,
    private location: Location,
    private readonly translate: TranslateService) {}

  ngOnInit(): void {
    this.openGame = {} as OpenGameCommand;
    this.getAllUsernames();
    this.getSessions();
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.openGame !== null && this.openGame !== undefined) {
      this.openGame.usersInGame = this.addedUsernames;
      this.openGameService.addOpenGame(this.openGame)
        .subscribe(() => this.goBack());
    }
  }

  getAllUsernames(): void {
    this.userService.getUsernames()
      .subscribe(usernames => this.notAddedUsernames = usernames);
  }

  addUsername(username: Username): void {
    this.addedUsernames.push(username);
    this.notAddedUsernames.splice(this.notAddedUsernames.indexOf(username), 1);
  }

  removeUsername(username: Username): void {
    this.notAddedUsernames.push(username);
    this.addedUsernames.splice(this.addedUsernames.indexOf(username), 1)
  }

  getSessions(): void {
    this.sessionService.getSessions(0, 100, false)
      .subscribe(sessions => this.sessions = sessions);
  }
}
