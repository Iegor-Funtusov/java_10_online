import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { map, Observable } from "rxjs";

import { DataTableResponseData } from "../models/data-table-response.data";
import { PlpData } from "../models/plp.data";
import { PdpData } from "../models/pdp.data";
import { ResponseContainerData } from "../models/response-container.data";
import { ProductIndexData } from "../models/product-index.data";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url: string = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) { }

  loadProducts(): Observable<DataTableResponseData<PlpData>> {
    return this.http.get<ResponseContainerData<DataTableResponseData<PlpData>>>(this.url)
      .pipe(
        map(res => res.data)
      );
  }

  loadProductById(productId: string): Observable<PdpData> {
    return this.http.get<ResponseContainerData<PdpData>>(`${this.url}/${productId}`)
      .pipe(
        map(res => res.data)
      );
  }

  searchProducts(search: string): Observable<ProductIndexData[]> {
    return this.http.get<ResponseContainerData<ProductIndexData[]>>(`${this.url}/search?search=${search}`)
      .pipe(
        map(res => res.data)
      );
  }
}
