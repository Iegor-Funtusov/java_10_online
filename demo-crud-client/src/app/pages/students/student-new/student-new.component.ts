import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {StudentsService} from "../../../services/students.service";
import {StudentModel} from "../../../models/student.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-student-new',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './student-new.component.html',
  styleUrl: './student-new.component.scss'
})
export class StudentNewComponent {

  form: FormGroup = this._fb.group({
    firstName: [null, Validators.required],
    lastName: [null, Validators.required],
    age: [null, Validators.required]
  })

  constructor(
    private _router: Router,
    private _fb: FormBuilder,
    private _service: StudentsService) {
  }

  create(): void {
    const student: StudentModel = { ...this.form.value };
    this._service.create(student)
      .subscribe(
        () => this._router.navigateByUrl('/students'),
        (error) => console.log('error', error),
      )
  }
}
