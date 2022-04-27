import { Injectable } from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  translatedMessage: string;

  constructor(private readonly toastrService: ToastrService,
              private readonly translate: TranslateService) {
    this.translatedMessage = "";
  }

  public success(message: string) {
    this.translate.get(message).subscribe(message => this.translatedMessage = message);
    this.toastrService.success(this.translatedMessage);
  }

  public error(message: string) {
    this.translate.get(message).subscribe(message => this.translatedMessage = message);
    this.toastrService.error(this.translatedMessage);
  }
}
