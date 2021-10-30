import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarComponent } from './nav-bar.component';
import {By} from "@angular/platform-browser";

describe('NavBarComponent', () => {
  let component: NavBarComponent;
  let fixture: ComponentFixture<NavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have exercises button', () => {
    const exercisesButton = fixture.debugElement.query(By.css('#exercisesBtn'));
    expect(exercisesButton).toBeTruthy();
    expect(exercisesButton.nativeElement.innerHTML).toBe("Exercises");
  })
});
