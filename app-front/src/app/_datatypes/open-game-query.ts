import {Session} from "./session";

export interface OpenGameQuery {
  id: number
  name: string;
  creator: string;
  timeToGuessInSeconds: number;
  usersInGameNames: string[];
  createdDate: string;
  session: Session;
  isFinished: boolean;
}
