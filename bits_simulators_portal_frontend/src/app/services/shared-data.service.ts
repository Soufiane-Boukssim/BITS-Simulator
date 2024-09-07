import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  constructor() { }
  PAGE_TITLE = new BehaviorSubject<any>(null)
  ID = new BehaviorSubject<any>("")
}
