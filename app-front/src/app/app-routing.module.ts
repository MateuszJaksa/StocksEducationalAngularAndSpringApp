import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SessionsComponent} from "./sessions/sessions.component";
import {SessionDetailsComponent} from "./session-details/session-details.component";
import {RegisterComponent} from "./register/register.component";
import {SessionAddComponent} from "./session-add/session-add.component";
import {UserStatsComponent} from "./user-stats/user-stats.component";
import {OpenGameDetailsComponent} from "./open-game-details/open-game-details.component";
import {OpenGamesComponent} from "./open-games/open-games.component";
import {OpenGameCreateComponent} from "./open-game-create/open-game-create.component";
import {PlayGameComponent} from "./play-game/play-game.component";
import {ManualComponent} from "./manual/manual.component";

const routes: Routes = [
  { path: '', component: ManualComponent},
  { path: 'session-detail/:id', component: SessionDetailsComponent },
  { path: 'sessions', component: SessionsComponent },
  { path: 'game-detail/:id', component: OpenGameDetailsComponent },
  { path: 'open-games', component: OpenGamesComponent },
  { path: 'game-add', component: OpenGameCreateComponent },
  { path: 'session-add', component: SessionAddComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'play-game/:id', component: PlayGameComponent },
  { path: 'user-stats', component: UserStatsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
