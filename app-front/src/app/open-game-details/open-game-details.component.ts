import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {OpenGameQuery} from "../_datatypes/open-game-query";
import {OpenGameService} from "../_services/open-game.service";
import {SessionService} from "../_services/session.service";
import {GameResults} from "../_datatypes/game-results";
import {PlayGameService} from "../_services/play-game.service";
import {ChartService} from "../_services/chart.service";
import {ChartData} from "../_datatypes/chart-data";
import {TranslateService} from "@ngx-translate/core";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";

@Component({
  selector: 'app-open-game-details',
  templateUrl: './open-game-details.component.html',
  styleUrls: ['./open-game-details.component.css']
})
export class OpenGameDetailsComponent implements OnInit {

  openGame: OpenGameQuery | undefined;
  gameResults: GameResults | undefined;
  chartData: ChartData[] | undefined;
  isGraphDisplayed: boolean;
  isBestResultShortSold: boolean;
  isLoggedUserInGame: boolean;

  constructor(
    private route: ActivatedRoute,
    private openGameService: OpenGameService,
    private sessionService: SessionService,
    private playGameService: PlayGameService,
    private chartService: ChartService,
    private location: Location,
    private readonly translate: TranslateService) {
    this.isGraphDisplayed = false;
    this.isBestResultShortSold = false;
    this.isLoggedUserInGame = false;
  }

  async ngOnInit() {
    this.getOpenGame();
  }

  getOpenGame(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.openGameService.getOpenGameById(id)
      .subscribe(openGame => this.openGame = openGame);
    this.playGameService.getGameResults(id)
      .subscribe(gameResults => {this.gameResults = gameResults; this.changeIsShortSold(gameResults)});

  }

  goBack(): void {
    this.location.back();
  }

  changeIsShortSold(gameResult: GameResults): void {
    if (Date.parse(gameResult.bestResults.sellDate) < Date.parse(gameResult.bestResults.buyDate)) {
      this.isBestResultShortSold = true;
    }
  }

  showGraph(): void {
    if (this.openGame !== undefined) {
      this.chartService.getChartData(this.openGame.session.id)
        .subscribe(chartData => this.chartData = chartData);
      this.isGraphDisplayed = true;
    }
  }
}
