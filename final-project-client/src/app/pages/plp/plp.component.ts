import { Component, OnInit } from '@angular/core';
import { ProductService } from "../../services/product.service";
import {NgForOf, NgIf} from "@angular/common";
import {DataTableResponseData} from "../../models/data-table-response.data";
import {PlpData} from "../../models/plp.data";
import {Router} from "@angular/router";

@Component({
  selector: 'app-plp',
  standalone: true,
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './plp.component.html',
})
export class PlpComponent implements OnInit {

  isLoading: boolean = false;
  data?: DataTableResponseData<PlpData>;

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.productService.loadProducts()
      .subscribe(res => {
        this.isLoading = false;
        this.data = res;
      });
  }

  goToProduct(productId: number): void {
    this.router.navigateByUrl(`pdp/${productId}`)
  }
}
