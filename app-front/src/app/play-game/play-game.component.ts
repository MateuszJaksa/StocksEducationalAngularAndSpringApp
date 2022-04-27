import { Component, OnInit } from '@angular/core';
import {OpenGameQuery} from "../_datatypes/open-game-query";
import {ActivatedRoute} from "@angular/router";
import {OpenGameService} from "../_services/open-game.service";
import {Location} from "@angular/common";
import {CompanyBet, PlayedGame} from "../_datatypes/played-game";
import {PlayGameService} from "../_services/play-game.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-play-game',
  templateUrl: './play-game.component.html',
  styleUrls: ['./play-game.component.css']
})
export class PlayGameComponent implements OnInit {

  openGame: OpenGameQuery | undefined;
  companyBets: CompanyBet[];
  remainingFunds: number;
  sessionDays: string[];
  timeLeft: number;
  interval: any;
  sentData: boolean;

  constructor(
    private route: ActivatedRoute,
    private openGameService: OpenGameService,
    private playGameService: PlayGameService,
    private location: Location,
    private readonly translate: TranslateService) {
    this.companyBets = [] as CompanyBet[];
    this.remainingFunds = 0;
    this.timeLeft = 0;
    this.sessionDays = [] as string[];
    this.sentData = false;
  }

  ngOnInit(): void {
    this.getOpenGame();
  }

  getOpenGame(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.openGameService.getOpenGameById(id)
      .subscribe(openGame => {this.openGame = openGame; this.getDataAboutGame()});
  }

  getDataAboutGame(): void {
    if (this.openGame !== undefined) {
      this.remainingFunds = this.openGame?.session.funds;
      this.playGameService.getSessionDays(this.openGame.session.id)
        .subscribe(sessionDays => this.sessionDays = sessionDays.sessionDays);
      const companies = this.openGame.session.companies;
      this.timeLeft = this.openGame.timeToGuessInSeconds;
      this.startTimer();
      this.companyBets = companies.map(company => {return <CompanyBet> {
        companyAbbreviation: company.fullName,
        moneySpent: 0,
        dayBuying: '',
        daySelling: ''
      }})
    }
  }

  onChange(): void {
    if (this.openGame !== undefined) {
      this.remainingFunds = this.openGame?.session.funds;
      for (const bet of this.companyBets) {
        this.remainingFunds -= bet.moneySpent;
      }
    }
  }

  save(): void {
    if (this.companyBets.length > 0) {
      this.sentData = true;
      const id = this.route.snapshot.paramMap.get('id')!;
      this.playGameService.addPlayedGame(id, new PlayedGame(this.companyBets))
        .subscribe(() => this.location.back());
    }
  }

  goBack(): void {
    if (!this.sentData) {
      this.sendEmptyRequest();
    }
    this.location.back();
  }

  startTimer() {
    this.interval = setInterval(() => {
      if(this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        if (!this.sentData) {
          this.sendEmptyRequest();
          this.openGame = undefined;
        }
      }
    },1000)
  }

  sendEmptyRequest() {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.playGameService.addMockGame(id)
      .subscribe(() => this.goBack());
  }
}
