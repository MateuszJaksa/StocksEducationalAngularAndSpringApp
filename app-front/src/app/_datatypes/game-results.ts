export interface GameResults {
  yourScore: number;
  otherResultsWithUsernames: KeyValuePair[];
  bondsResult: number;
  bestResults: BestResults
}

interface KeyValuePair {
  username: string;
  result: number;
}

interface BestResults {
  companyName: string;
  earnings: number;
  buyDate: string;
  sellDate: string;
}
