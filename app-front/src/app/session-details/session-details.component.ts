import {Component, Input, OnInit} from '@angular/core';
import {Session} from "../_datatypes/session";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {SessionService} from "../_services/session.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrls: ['./session-details.component.css']
})
export class SessionDetailsComponent implements OnInit {
  session: Session | undefined;
  langString: string;

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private location: Location,
    private readonly translate: TranslateService) {
    this.langString = "en";
  }

  ngOnInit(): void {
    this.getSessionAndLang();
  }

  getSessionAndLang(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.sessionService.getSession(id)
      .subscribe(session => this.session = session);
    this.translate.onLangChange.subscribe(lang => this.langString = lang.lang);
  }

  goBack(): void {
    this.location.back();
  }
}
