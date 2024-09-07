import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
// const enterTransition = transition(':enter',[
//   style({
//     opacity:0
//   }),
//   animate('1s ease-in',style({opacity:1}))
// ])
// const fadeIn = trigger('fadeIn',[enterTransition])
// const exitTransition = transition(':leave',[
//   style({
//     opacity:1
//   }),
//   animate('1s ease-out',style({opacity:0}))
// ])
// const fadeOut = trigger('fadeOut',[exitTransition])
//v2
const fadeInOut = trigger('fadeInOut',[
  state('open',style({
    opacity:1,
    right:'1rem'
  })),
  state('close',style({
    opacity:0,
    right:'-10rem'
  })),
  transition('open => close', [animate('1s ease-out')]),
  transition('close => open', [animate('1s ease-in')])
])
@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss'],
  animations:[fadeInOut]
  // animations: [fadeIn, fadeOut],
})
export class AlertComponent implements OnInit {
  @Input() status:string = ""
  @Input() message:string = ""
  ngOnInit(): void {
  }
  @Input() isAlertVisible: boolean = false;
  closeAlert(){
    this.isAlertVisible = false
  }
}
