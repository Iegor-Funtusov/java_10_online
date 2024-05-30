import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { AsyncPipe, NgForOf, NgIf } from "@angular/common";
import { BehaviorSubject, filter, map, Observable, Subscription, switchMap } from "rxjs";

import { ProductService } from "../../services/product.service";
import { ProductIndexData } from "../../models/product-index.data";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    AsyncPipe,
    NgIf,
    NgForOf
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  private searchResultSub$
    = new BehaviorSubject<ProductIndexData[]>([]);
  searchResult$: Observable<ProductIndexData[]> = this.searchResultSub$.asObservable();

  searchForm: FormGroup = this.fb.group({
    query: new FormControl()
  });

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private productService: ProductService) {
  }

  ngOnInit(): void {
    this.subscription.add(
      this.searchForm.valueChanges
        .pipe(
          filter(value => value?.query?.length >= 3),
          map(value => value?.query),
          switchMap(query => this.productService.searchProducts(query))
        )
        .subscribe((res: ProductIndexData[]) => this.searchResultSub$.next(res))
    );
  }

  navigateToProduct(data: ProductIndexData): void {
    const arr: String[] = data.productInfo.split(",")
    const cpu = arr[1].trim();
    const os = arr[2].trim();
    this.router.navigate([`pdp/${data.productId}`], {
      queryParams: { cpu, os }
    })
  }

  ngOnDestroy(): void {
    this.searchResultSub$.complete();
    this.subscription.unsubscribe();
  }
}
