export class PlayedGame {
  companyBets: CompanyBet[]

  constructor(_companyBets : CompanyBet[]) {
    this.companyBets = _companyBets;
  }
}

export interface CompanyBet {
  companyAbbreviation: string,
  moneySpent: number,
  dayBuying: string,
  daySelling: string
}
