import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {WorkoutsListComponent} from './workouts-list.component';
import {By} from "@angular/platform-browser";
import {WorkoutService} from "../shared/workout.service";
import {WorkoutServiceStubs} from "../../testing/workout-stubs";
import {HttpClientModule} from "@angular/common/http";

describe('WorkoutsListComponent', () => {
  let component: WorkoutsListComponent;
  let fixture: ComponentFixture<WorkoutsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WorkoutsListComponent],
      imports: [HttpClientModule],
      providers: [
        {provide: WorkoutService, useClass: WorkoutServiceStubs}
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', fakeAsync(() => {
    expect(component).toBeTruthy();
  }));

  it('should have workout list', fakeAsync(() => {
    const workoutHeader = fixture.debugElement.queryAll(By.css('h2'));
    expect(workoutHeader[0].nativeElement.innerHTML).toBe("Workouts");
    const workoutsListElements = fixture.debugElement.queryAll(By.css('mat-list-item'));
    expect(workoutsListElements.length).toBe(2);
  }));
});
