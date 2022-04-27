import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SessionsComponent } from './sessions/sessions.component';
import { FormsModule } from "@angular/forms";
import { SessionDetailsComponent } from './session-details/session-details.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { RegisterComponent } from './register/register.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { SessionAddComponent } from './session-add/session-add.component';
import { UserStatsComponent } from './user-stats/user-stats.component';
import { OpenGamesComponent } from './open-games/open-games.component';
import { OpenGameDetailsComponent } from './open-game-details/open-game-details.component';
import { OpenGameCreateComponent } from './open-game-create/open-game-create.component';
import { FactsComponent } from './facts/facts.component';
import { PlayGameComponent } from './play-game/play-game.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatListModule} from "@angular/material/list";
import {MatSelectModule} from "@angular/material/select";
import { ManualComponent } from './manual/manual.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { ChartComponent } from './chart/chart.component';
import {LineChartModule} from "@swimlane/ngx-charts";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {TranslateModule, TranslateLoader} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://INSERT YOUR IP HERE:8080/auth',
        realm: 'app-realm',
        clientId: 'front-client',
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html',
      },
    });
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}


@NgModule({
  declarations: [
    AppComponent,
    SessionsComponent,
    SessionDetailsComponent,
    RegisterComponent,
    SessionAddComponent,
    UserStatsComponent,
    OpenGamesComponent,
    OpenGameDetailsComponent,
    OpenGameCreateComponent,
    FactsComponent,
    PlayGameComponent,
    ManualComponent,
    ChartComponent,
    ChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    KeycloakAngularModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    FlexLayoutModule,
    MatTableModule,
    MatListModule,
    MatSelectModule,
    MatCheckboxModule,
    LineChartModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
        timeOut: 5000,
        positionClass: 'toast-bottom-right'
      }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      },
      defaultLanguage: 'en'
    })
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
