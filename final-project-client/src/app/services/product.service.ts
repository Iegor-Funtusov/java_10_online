import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { DataTableResponseData } from "../models/data-table-response.data";
import { PlpData } from "../models/plp.data";
import {PdpData} from "../models/pdp.data";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url: string = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) { }

  loadProducts(): Observable<DataTableResponseData<PlpData>> {
    return this.http.get(this.url) as Observable<DataTableResponseData<PlpData>>;
  }

  loadProductById(productId: string): Observable<PdpData> {
    return this.http.get<PdpData>(`${this.url}/${productId}`);
  }
}
