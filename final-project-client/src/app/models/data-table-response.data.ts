export interface DataTableResponseData<T> {
  page: number;
  size: number;
  sort: string;
  order: string;
  totalPages: number;
  totalElements: number;
  hasContent: boolean;
  first: boolean;
  last: boolean;
  next: boolean;
  previous: boolean;
  items: T[];
}
