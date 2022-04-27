export interface UserStats {
  username: string;
  gamesPlayed: number;
  highestScore: number;
  averageScore: number;
  totalScore: number;
}

export interface UserBest {
  gameId: number;
  score: number;
  name: string;
  sessionName: string;
}
