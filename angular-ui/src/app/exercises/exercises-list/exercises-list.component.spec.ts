import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {ExercisesListComponent} from './exercises-list.component';
import {By} from "@angular/platform-browser";
import {ExerciseService} from "../shared/exercise.service";
import {ExerciseServiceStubs} from "../../testing/exercise-stubs";
import {HttpClientModule} from "@angular/common/http";

describe('ExercisesListComponent', () => {

  let component: ExercisesListComponent;
  let fixture: ComponentFixture<ExercisesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExercisesListComponent],
      imports: [HttpClientModule],
      providers: [
        {provide: ExerciseService, useClass: ExerciseServiceStubs}
      ]
    })
      .compileComponents();
  });

  beforeEach(fakeAsync(() => {
    fixture = TestBed.createComponent(ExercisesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', fakeAsync(() => {
    expect(component).toBeTruthy();
  }));

  it('should have exercise list', fakeAsync(() => {
    const exercisesElements = fixture.debugElement.queryAll(By.css('mat-list-item'));
    expect(exercisesElements.length).toBe(2);
  }))
});
