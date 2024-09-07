import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/services/global.service';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.scss']
})
export class AdministrationComponent implements OnInit{

  user1:any
  language = ""
  searchText: string = '';
  pp: number = 0
  p: number = 0;
  page:number = 1;
  usersPendingList:any =[];

  itemsPerPageSelctUserP:number=7;
  itemsPerPageSelctUser:number=7;
  usersList:any =
  [
    {userName:'Mohammed JOUBBA',role:'ADMIN',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Hamza JOUBBA',role:'ADMIN',lastActivity:'02-02-2023 09:08:42',status:'B'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'},
    {userName:'Oussama MEJDOUB',role:'USER',lastActivity:'02-02-2023 09:08:42',status:'U'}
  ]
  constructor(
    private globalService:GlobalService,
    private router:Router
  ){}
  ngOnInit(): void {

    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;
    if(this.language == "en"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Administration"));
    }
    else if(this.language == "fr"){
      Promise.resolve().then(() => this.globalService.titleSubject.next("Administration"));
    }
    else{
      Promise.resolve().then(() => this.globalService.titleSubject.next("Administración"));
    }
    //Promise.resolve().then(() => this.globalService.titleSubject.next("Administration"))
    this.getApprouvedUsers();
    this.getPendingUsers();
  }
  getApprouvedUsers(){
    let body = {
      status:'A'
    }
    this.globalService.getUsers(body).subscribe(response=>{
      // console.log('response: ', response);
      this.usersList = response.result
    })
  }

  getPendingUsers(){
    let body = {
      status:'P'
    }
    this.globalService.getUsers(body).subscribe(response=>{
      // console.log('response: ', response);
      this.usersPendingList = response.result;
    })
  }
  goToUserDetails(userCode:string){
    this.p=1;
    this.router.navigate(['/administration/user-details', userCode]);
  }

  goToAddUserComponent(){
    this.p=1;
    this.router.navigate(['/administration/add-user']);
  }

  get filteredUserList() {
    // console.log('this.usersList',this.usersList);
  
    // return this.usersList.filter((item:any) =>
    //   item.userCode.toLowerCase().includes(this.searchText.toLowerCase()) /*||*/
    //   //item.wording.toLowerCase().includes(this.searchText.toLowerCase())
      
    // );
    return this.usersList.filter((item: any) => {
      const userCode = item.userCode || '';
      return userCode.toLowerCase().includes(this.searchText.toLowerCase());
      // If you want to include the wording property in the search
      // const wording = item.wording || '';
      // return userCode.toLowerCase().includes(this.searchText.toLowerCase()) || 
      //        wording.toLowerCase().includes(this.searchText.toLowerCase());
    });
  }
}
