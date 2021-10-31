import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {Exercise} from "../exercises/shared/exercise.model";
import {Workout} from "../workouts/shared/workout.model";

const MOCKED_LIST: Workout[] = [
  {id: 1, name: "5x5"},
  {id: 2, name: "PPL"}
]

@Injectable()
export class WorkoutServiceStubs {
  getAll(): Observable<Exercise[]> {
    return of(MOCKED_LIST);
  }
}
