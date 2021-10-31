import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ExercisesListComponent} from "./exercises/exercises-list/exercises-list.component";
import {WorkoutsListComponent} from "./workouts/workouts-list/workouts-list.component";

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'workouts-list', component: WorkoutsListComponent},
  { path: 'exercises-list', component: ExercisesListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
