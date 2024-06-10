import { StockIndexData } from "./stock-index.data";

export interface StockIndexViewData extends StockIndexData {
  isUp: boolean;
  isDown: boolean;
  isNever: boolean
}
