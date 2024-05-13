import { ProductVariantData } from "./product-variant.data";

export interface PdpData {
  id: number;
  name: string;
  description: string;
  productBrand: string;
  height: number;
  width: number;
  depth: number;
  weight: number;
  images: string[];
  variants: ProductVariantData[];
}
