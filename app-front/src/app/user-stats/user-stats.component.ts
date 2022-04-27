import { Component, OnInit } from '@angular/core';
import {UserBest, UserStats} from "../_datatypes/user-stats";
import {UserStatsService} from "../_services/user-stats.service";
import {Location} from '@angular/common';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-user-stats',
  templateUrl: './user-stats.component.html',
  styleUrls: ['./user-stats.component.css']
})
export class UserStatsComponent implements OnInit {

  userStats: UserStats | undefined;
  userBests: UserBest[] = [];

  constructor(
    private userStatsService : UserStatsService,
    private location: Location,
    private readonly translate: TranslateService) {
  }

  ngOnInit(): void {
    this.getUserStats();
    this.getUserBests();
  }

  getUserStats(): void {
    this.userStatsService.getUserStats()
      .subscribe(userStats => this.userStats = userStats);
  }

  getUserBests(): void {
    this.userStatsService.getUserBests()
      .subscribe(userBests => this.userBests = userBests);
  }

  goBack(): void {
    this.location.back();
  }
}
