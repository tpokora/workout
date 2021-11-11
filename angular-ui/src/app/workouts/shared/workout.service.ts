import {Injectable} from "@angular/core";
import {Workout} from "./workout.model";
import {RestService} from "../../core/rest.service";

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  constructor(private restService: RestService) {
  }

  getAll(): Workout[] {
    let workoutsList: any = [];
    this.restService.get('workouts')
      .subscribe(workouts => {
        for (const workout of (workouts as Workout[])) {
          console.log(workout)
          workoutsList.push({
            id: workout.id,
            name: workout.name
          });
        }
      });
    return workoutsList;
  }
}
