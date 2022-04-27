import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from 'keycloak-js';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn = false;
  userProfile: KeycloakProfile | null = null;

  constructor(private readonly keycloak: KeycloakService,
              private readonly router: Router,
              private readonly translate: TranslateService) {}

  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }

  useLanguage(language: string): void {
    this.translate.use(language);
  }

  public login() {
    this.keycloak.login();
  }

  public logout() {
    this.keycloak.logout();
    this.router.navigate(['/']);
  }
}
