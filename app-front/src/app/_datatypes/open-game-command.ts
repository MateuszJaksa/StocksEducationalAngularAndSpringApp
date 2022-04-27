import {Username} from "./username";

export interface OpenGameCommand {
  name: string;
  timeToGuessInSeconds: number;
  sessionId: number;
  usersInGame: Username[];
}
