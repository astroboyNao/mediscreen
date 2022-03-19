import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NoteListComponent} from './note-list.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {AppComponent} from "../../app.component";

describe('PatientListComponent', () => {
  let component: NoteListComponent;
  let fixture: ComponentFixture<NoteListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({

      imports: [HttpClientTestingModule],
      declarations: [NoteListComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
