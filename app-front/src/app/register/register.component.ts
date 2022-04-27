import { Component, OnInit } from '@angular/core';
import {RegisteringUser} from "../_datatypes/registering-user";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../_services/user.service";
import {Location} from '@angular/common';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: RegisteringUser;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    private readonly translate: TranslateService
  ) {this.user = {username: '', password: ''};}

  ngOnInit(): void {
    this.user = {} as RegisteringUser;
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    const possibleUser = this.userService.addUser(this.user);
    possibleUser.subscribe(() => this.goBack());
  }
}
