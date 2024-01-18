import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentModel} from "../models/student.model";

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  private _url: string = 'http://localhost:8080/students'

  constructor(private _http: HttpClient) { }

  create(student: StudentModel): Observable<boolean> {
    return this._http.post<boolean>(this._url, student);
  }

  update(student: StudentModel, id: number): Observable<boolean> {
    return this._http.put<boolean>(`${this._url}/${id}`, student);
  }

  delete(id: number): Observable<boolean> {
    return this._http.delete<boolean>(`${this._url}/${id}`);
  }

  findById(id: number): Observable<StudentModel> {
    return this._http.get<StudentModel>(`${this._url}/${id}`);
  }

  findAll(): Observable<StudentModel[]> {
    return this._http.get<StudentModel[]>(`${this._url}`);
  }
}
