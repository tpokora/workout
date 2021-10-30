import {Injectable} from "@angular/core";
import {Exercise} from "../exercises/shared/exercise.model";
import {Observable, of} from "rxjs";

const MOCKED_LIST: Exercise[] = [
  {name: "Rows"}, {name: "Deadlift"}
];

@Injectable()
export class ExerciseServiceStubs {
  getAll(): Observable<Exercise[]> {
    return of(MOCKED_LIST);
  }
}
