import { Component, OnInit } from '@angular/core';
import {Workout} from "../shared/workout.model";
import {WorkoutService} from "../shared/workout.service";

@Component({
  selector: 'app-workouts-list',
  templateUrl: './workouts-list.component.html',
  styleUrls: ['./workouts-list.component.css']
})
export class WorkoutsListComponent implements OnInit {

  private workoutsService: WorkoutService;
  workoutsList: Workout[]

  constructor(workoutsService: WorkoutService) {
    this.workoutsService = workoutsService;
    this.workoutsList = [];
  }

  ngOnInit(): void {
    this.workoutsList = this.workoutsService.getAll();
  }
}
