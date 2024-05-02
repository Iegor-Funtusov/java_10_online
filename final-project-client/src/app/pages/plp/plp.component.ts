import { Component, OnInit } from '@angular/core';
import { PlpService } from "../../services/plp.service";
import {NgForOf, NgIf} from "@angular/common";
import {DataTableResponseData} from "../../models/data-table-response.data";
import {PlpData} from "../../models/plp.data";

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

  constructor(private plpService: PlpService) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.plpService.loadProducts()
      .subscribe(res => {
        this.isLoading = false;
        this.data = res;
      });
  }
}
