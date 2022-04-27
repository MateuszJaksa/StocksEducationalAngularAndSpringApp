export interface ChartData {
  name: string;
  series: ChartDetail[];
}

export interface ChartDetail{
  name: string;
  value: number;
}
