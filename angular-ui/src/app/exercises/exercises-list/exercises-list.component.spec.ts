import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExercisesListComponent } from './exercises-list.component';
import {Exercise} from "../shared/exercise.model";
import {By} from "@angular/platform-browser";

describe('ExercisesListComponent', () => {

  const MOCKED_LIST: Exercise[] = [
    {name: "Rows"}, {name: "Deadlift"}
  ];

  let component: ExercisesListComponent;
  let fixture: ComponentFixture<ExercisesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExercisesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExercisesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have exercise list', () => {
    component.exercisesList = MOCKED_LIST;
    const exercisesElements = fixture.debugElement.queryAll(By.css('mat-list-item'));
    expect(exercisesElements.length).toBe(2);
  })
});
