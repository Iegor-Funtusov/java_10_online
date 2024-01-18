import { Component } from '@angular/core';
import {StudentsService} from "../../../services/students.service";
import {Observable} from "rxjs";
import {StudentModel} from "../../../models/student.model";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    NgForOf,
    RouterLink
  ],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent {

  students$: Observable<StudentModel[]> = this._service.findAll();

  constructor(private _service: StudentsService) {
  }

  delete(id: number): void {
    this._service.delete(id)
      .subscribe(
        () => this.students$ = this._service.findAll(),
        (error) => console.log('error',error)
      );
  }
}
