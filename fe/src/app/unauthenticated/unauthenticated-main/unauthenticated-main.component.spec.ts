/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { UnauthenticatedMainComponent } from './unauthenticated-main.component';

describe('UnauthenticatedMainComponent', () => {
  let component: UnauthenticatedMainComponent;
  let fixture: ComponentFixture<UnauthenticatedMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnauthenticatedMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthenticatedMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
