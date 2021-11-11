import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutsAddComponent } from './workouts-add.component';

describe('WorkoutsAddComponent', () => {
  let component: WorkoutsAddComponent;
  let fixture: ComponentFixture<WorkoutsAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WorkoutsAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
