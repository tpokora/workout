import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {Workout} from "./workout.model";
import {RestService} from "../../core/rest.service";

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  private MOCKED_LIST: Workout[] = [
    {id: 1, name: "5x5"},
    {id: 2, name: "PPL"}
  ]

  constructor(restService: RestService<Workout>) {
  }

  getAll(): Observable<Workout[]> {
    return of(this.MOCKED_LIST);
  }
}
