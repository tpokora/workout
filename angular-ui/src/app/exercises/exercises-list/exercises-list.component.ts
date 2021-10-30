import { Component, OnInit } from '@angular/core';
import {Exercise} from "../shared/exercise.model";

@Component({
  selector: 'app-exercises-list',
  templateUrl: './exercises-list.component.html',
  styleUrls: ['./exercises-list.component.css']
})
export class ExercisesListComponent implements OnInit {

  private MOCKED_LIST: Exercise[] = [
    {name: "Rows"}, {name: "Deadlift"}
  ];

  exercisesList: Exercise[]

  constructor() {
    this.exercisesList = [];
  }

  ngOnInit(): void {
    this.exercisesList = this.MOCKED_LIST
  }

}
