import {Component, Input} from '@angular/core';
import {ChartData} from "../_datatypes/chart-data";
import {Color} from "@swimlane/ngx-charts";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent {
  @Input("data") chartData: any[];
  view: [number, number] = [800, 400];

  legend: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Day';
  yAxisLabel: string = 'Price';
  timeline: boolean = true;

  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  } as Color;

  constructor() {
    this.chartData = [] as ChartData[];
  }
}
