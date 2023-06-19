import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebshopToolbarComponent } from './webshop-toolbar.component';

describe('WebshopToolbarComponent', () => {
  let component: WebshopToolbarComponent;
  let fixture: ComponentFixture<WebshopToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WebshopToolbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebshopToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
