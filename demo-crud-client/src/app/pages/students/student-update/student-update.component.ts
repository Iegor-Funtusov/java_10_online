import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {StudentsService} from "../../../services/students.service";
import {StudentModel} from "../../../models/student.model";

@Component({
  selector: 'app-student-update',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './student-update.component.html',
  styleUrl: './student-update.component.scss'
})
export class StudentUpdateComponent implements OnInit {

  private _id: number = 0;

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

  ngOnInit(): void {
    const url = this._router.routerState.snapshot.url;
    console.log('url', url.split('/'))
    const id = Number.parseInt(url.split('/')[2])

    console.log('id', id)

    this._id = id;

    this._service.findById(id)
      .subscribe(student => {
        this.form.controls['firstName'].setValue(student.firstName);
        this.form.controls['lastName'].setValue(student.lastName);
        this.form.controls['age'].setValue(student.age);
      })
  }

  update(): void {
    const student: StudentModel = { ...this.form.value };
    this._service.update(student, this._id)
      .subscribe(
        () => this._router.navigateByUrl('/students'),
        (error) => console.log('error', error),
      )
  }
}
