/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FuckSantaoComponent } from './fuck-santao.component';

describe('FuckSantaoComponent', () => {
  let component: FuckSantaoComponent;
  let fixture: ComponentFixture<FuckSantaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuckSantaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuckSantaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
