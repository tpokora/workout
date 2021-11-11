import {RestService} from "../../core/rest.service";
import {Injectable} from "@angular/core";
import {Exercise} from "./exercise.model";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private MOCKED_LIST: Exercise[] = [
    {name: "Rows"}, {name: "Deadlift"}
  ];

  constructor(private restService: RestService) {}

  getAll(): Observable<Exercise[]> {
    return of(this.MOCKED_LIST);
  }
}
