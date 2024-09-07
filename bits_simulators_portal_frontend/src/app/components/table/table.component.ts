import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SharedDataService } from 'src/app/services/shared-data.service';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
  @Input() arrayHeader:string[] = []
  @Input() arrayGrid:any[] = []
  @Input() numberOfLines:number = 5
  @Input() dataLength:number = 80
  @Output() onSelect = new EventEmitter<any>()
  p: number = 1;
  modalAppear: boolean = false;
  constructor(
    private shared:SharedDataService
  ){}
  ngOnInit(): void {

  }
  openModal(id:string){
    // console.log('id: ', id);
    this.shared.ID.next(id)
  }
  select(item:any){
    this.onSelect.emit(item);
  }

  cleanUnicode(value: string): string {
    // Filtrer les caractères Unicode spéciaux et les remplacer par une chaîne vide
    return value.replace(/[^\x20-\x7E]+/g, '');
   }
}
