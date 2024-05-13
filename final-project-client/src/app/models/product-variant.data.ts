import { ProductDisplayData } from "./product-display.data";

export interface ProductVariantData {
  id: number;
  os: string;
  cpu: string;
  ram: number;
  ssd: number;
  color: string;
  battery: string;
  camera: string;
  price: string;
  wireless: string;
  productDisplay: ProductDisplayData
}
