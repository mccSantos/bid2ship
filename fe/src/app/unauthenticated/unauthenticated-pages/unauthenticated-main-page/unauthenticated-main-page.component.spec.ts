import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthenticatedMainPageComponent } from './unauthenticated-main-page.component';

describe('UnauthenticatedMainPageComponent', () => {
  let component: UnauthenticatedMainPageComponent;
  let fixture: ComponentFixture<UnauthenticatedMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatedMainPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UnauthenticatedMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
