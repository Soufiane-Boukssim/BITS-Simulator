import { Component } from '@angular/core';
import {GlobalService} from "../../../services/global.service";

@Component({
  selector: 'app-scenario',
  templateUrl: './scenario.component.html',
  styleUrls: ['./scenario.component.scss']
})
export class ScenarioComponent {
  constructor(
    private globalService:GlobalService
  ){}
  ngOnInit(): void {
    Promise.resolve().then(() => this.globalService.titleSubject.next("Scenario"))
  }
}
