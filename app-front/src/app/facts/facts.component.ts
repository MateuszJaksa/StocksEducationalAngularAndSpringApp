import { Component, OnInit } from '@angular/core';
import {NavigationStart, Router} from "@angular/router";
import {FACTS_EN} from "../randomFacts";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-facts',
  templateUrl: './facts.component.html',
  styleUrls: ['./facts.component.css']
})
export class FactsComponent implements OnInit {

  fact: string;

  constructor(public router: Router,
              private readonly translate: TranslateService) {
    this.fact = FACTS_EN[0];
  }

  ngOnInit(): void {
    this.router.events.subscribe(event =>{
      if (event instanceof NavigationStart){
        this.drawFact();
      }
    })
  }

  drawFact() {
    const num = Math.floor(Math.random() * FACTS_EN.length);
    this.translate.get(FACTS_EN[num]).subscribe(fact => this.fact = fact);
  }
}
