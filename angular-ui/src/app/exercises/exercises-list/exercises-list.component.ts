import {Component, OnInit} from '@angular/core';
import {Exercise} from "../shared/exercise.model";
import {ExerciseService} from "../shared/exercise.service";

@Component({
  selector: 'app-exercises-list',
  templateUrl: './exercises-list.component.html',
  styleUrls: ['./exercises-list.component.css']
})
export class ExercisesListComponent implements OnInit {

  private exerciseService: ExerciseService;

  exercisesList: Exercise[]

  constructor(exerciseService: ExerciseService) {
    this.exerciseService = exerciseService;
    this.exercisesList = [];
  }

  ngOnInit(): void {
    this.exerciseService.getAll().subscribe(exercises => this.exercisesList = exercises)
  }

}
