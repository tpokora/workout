import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {MaterialModule} from "./material.module";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {ExercisesListComponent} from './exercises/exercises-list/exercises-list.component';
import {ExerciseService} from "./exercises/shared/exercise.service";
import {RestService} from "./core/rest.service";
import {HttpClientModule} from "@angular/common/http";
import { WorkoutsListComponent } from './workouts/workouts-list/workouts-list.component';
import {WorkoutService} from "./workouts/shared/workout.service";
import { WorkoutsAddComponent } from './workouts/workouts-add/workouts-add.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    ExercisesListComponent,
    WorkoutsListComponent,
    WorkoutsAddComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [
    RestService,
    ExerciseService,
    WorkoutService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

