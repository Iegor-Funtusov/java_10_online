import { Injectable, NgZone } from '@angular/core';
import { Observable } from "rxjs";
import { StockIndexData } from "./stock-index.data";

@Injectable({
  providedIn: 'root'
})
export class StockIndexService {

  private eventSource: any = window['EventSource'];

  constructor(private ngZone: NgZone) { }

  load(): Observable<StockIndexData> {
    return new Observable<StockIndexData>(obs => {
      const eventSource = new this.eventSource('http://localhost:8080/api/indexes');
      eventSource.onmessage = (event: { data: string; }) => {
        const data = JSON.parse(event.data);
        this.ngZone.run(() => obs.next(data))
      }
      return () => eventSource.close();
    });
  }
}
