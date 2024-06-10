import {Component, OnDestroy, OnInit} from '@angular/core';
import {StockIndexService} from "./stock-index.service";
import {BehaviorSubject, map, Observable, Subscription, switchMap, take, tap} from "rxjs";
import {StockIndexViewData} from "./stock-index-view.data";
import {AsyncPipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-stock-index',
  standalone: true,
  imports: [
    NgForOf,
    AsyncPipe
  ],
  templateUrl: './stock-index.component.html',
  styleUrl: './stock-index.component.scss'
})
export class StockIndexComponent implements OnInit, OnDestroy {

  private subscription = new Subscription();
  private sub = new BehaviorSubject<Map<string, StockIndexViewData>>(
    new Map()
      .set('Stock Index A', {
        name: 'Stock Index A',
        currentValue: 0.0,
        maxValue: 0.0,
        minValue: 0.0,
        isUp: false,
        isDown: false,
        isNever: true
      })
      .set('Stock Index B', {
        name: 'Stock Index B',
        currentValue: 0.0,
        maxValue: 0.0,
        minValue: 0.0,
        isUp: false,
        isDown: false,
        isNever: true
      })
      .set('Stock Index C', {
        name: 'Stock Index C',
        currentValue: 0.0,
        maxValue: 0.0,
        minValue: 0.0,
        isUp: false,
        isDown: false,
        isNever: true
      })
  );

  data: Observable<StockIndexViewData[]> = this.sub.asObservable()
    .pipe(
      map(map => Array.from(map.values()))
    );

  constructor(private service: StockIndexService) { }

  ngOnInit(): void {
    this.subscription.add(
      this.service.load()
        .pipe(
          switchMap(data => this.sub.asObservable()
            .pipe(
              take(1),
              tap(map => {
                const newMap = new Map(map);
                const current = map.get(data.name);
                if (current) {
                  let newElement = { ...data, isUp: current.isUp, isDown: current.isDown, isNever: current.isNever };
                  if (current.currentValue < data.currentValue) {
                    newElement.isUp = true;
                    newElement.isDown = false;
                    newElement.isNever = false;
                  }
                  if (current.currentValue > data.currentValue) {
                    newElement.isUp = false;
                    newElement.isDown = true;
                    newElement.isNever = false;
                  }
                  if (current.currentValue === data.currentValue) {
                    newElement.isUp = false;
                    newElement.isDown = false;
                    newElement.isNever = true;
                  }
                  newMap.set(data.name, newElement);
                  this.sub.next(newMap);
                }
              })
            ))
        )
        .subscribe()
    )
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
