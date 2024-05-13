import {Component, OnInit} from '@angular/core';
import { ProductService } from "../../services/product.service";
import { PdpData } from "../../models/pdp.data";
import {NgForOf, NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {ProductVariantData} from "../../models/product-variant.data";
import {FormBuilder, FormControl, Validators} from "@angular/forms";

interface ProductVariantList {
  os: string[];
  cpu: string[];
  ram: number[];
  ssd: number[];
  color: string[];
  battery: string[];
  camera: string[];
  wireless: string[];
}

const defaultProductVariantList: ProductVariantList = {
  os: [],
  cpu: [],
  ram: [],
  ssd: [],
  color: [],
  battery: [],
  camera: [],
  wireless: [],
}

@Component({
  selector: 'app-pdp',
  standalone: true,
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './pdp.component.html',
  styleUrl: 'pdp.component.css'
})
export class PdpComponent implements OnInit {

  variant?: ProductVariantData;
  isLoading: boolean = false;
  product?: PdpData;
  variants: ProductVariantList = defaultProductVariantList;

  variantForm = this.fb.group({
    os: new FormControl(null, [Validators.required]),
    cpu: new FormControl(null, [Validators.required]),
    ram:new FormControl(null, [Validators.required]),
    ssd: new FormControl(null, [Validators.required]),
    color: new FormControl(null, [Validators.required]),
    battery: new FormControl(null, [Validators.required]),
    camera: new FormControl(null, [Validators.required]),
    wireless: new FormControl(null, [Validators.required]),
  });

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private productService: ProductService,
  ) {
  }

  ngOnInit(): void {
    const url = this.router.routerState.snapshot.url;
    const productId = url.split('/')?.[2];
    if (productId) {
      this.isLoading = true;
      this.productService.loadProductById(productId)
        .subscribe(product => {
          this.product = product;
          this.initVariants(product.variants);
          this.isLoading = false;
        });
    }

    this.variantForm.statusChanges.subscribe(status => {
      if (status === 'VALID') {
        const variant = this.product.variants.find(v => {
          const current = { ...this.variantForm.value };
          if (
            v.os === current.os &&
            v.cpu === current.cpu &&
            v.ssd === current.ssd &&
            v.ram === current.ram &&
            v.color === current.color &&
            v.wireless === current.wireless &&
            v.battery === current.battery &&
            v.camera === current.camera
          ) {
            return true;
          }
          return false;
        });

        if (variant) {
          this.variant = variant;
        }
      }
    })
  }

  selectVariant(variantKey: string, variantValue: number | string): void {
    this.variantForm.controls[variantKey].setValue(variantValue);
  }

  private initVariants(variants: ProductVariantData[]): void {
    const osItems = new Set<string>(variants.map(v => v.os));
    const cpuItems = new Set<string>(variants.map(v => v.cpu));
    const ramItems = new Set<number>(variants.map(v => v.ram));
    const ssdItems = new Set<number>(variants.map(v => v.ssd));
    const colorItems = new Set<string>(variants.map(v => v.color));
    const batteryItems = new Set<string>(variants.map(v => v.battery));
    const cameraItems = new Set<string>(variants.map(v => v.camera));
    const wirelessItems = new Set<string>(variants.map(v => v.wireless));
    this.variants = {
      os: Array.from(osItems),
      cpu: Array.from(cpuItems),
      ram: Array.from(ramItems),
      ssd: Array.from(ssdItems),
      color: Array.from(colorItems),
      battery: Array.from(batteryItems),
      camera: Array.from(cameraItems),
      wireless: Array.from(wirelessItems),
    };

    for (let variantsKey in this.variants) {
      const variant = this.variants[variantsKey];
      if (variant?.length === 1) {
        this.variantForm.controls[variantsKey].setValue(variant[0]);
      }
    }
  }
}
