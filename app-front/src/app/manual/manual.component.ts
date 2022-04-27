import { Component, OnInit } from '@angular/core';
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-manual',
  templateUrl: './manual.component.html',
  styleUrls: ['./manual.component.css']
})
export class ManualComponent implements OnInit {
  isLoggedIn = false;
  userProfile: KeycloakProfile | null = null;

  constructor(private readonly keycloak: KeycloakService,
              private readonly translate: TranslateService) {}

  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }
}
