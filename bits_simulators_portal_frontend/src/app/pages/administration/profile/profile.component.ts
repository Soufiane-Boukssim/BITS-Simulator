import { Component, OnInit } from '@angular/core';
import { ATMProfileService } from 'src/app/services/atm-profile.service';
import { ATMProfile } from 'src/app/models/atm-profile.model';
import { GlobalService } from 'src/app/services/global.service';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profileCode: string = '';
  profileWording: string = '';
  profileType: string = '';
  isEditing: boolean = false;
  profiles: ATMProfile[] = [];
  displayedProfiles: ATMProfile[] = [];
  profileTypes: string[] = [
    'State', 'Screen', 'Fit', 'Time Out & Param Config',
    'Receipt', 'Journal', 'Envelope', 'Distribution',
    'EMV', 'Cassette'
  ];
  successMessage: string = '';
  errorMessage: string = '';
  searchText: string = '';
  currentPage: number = 1;
  totalPages: number = 1;
  showPageList: boolean = false;

  language = "";
  user1: any = "";
  fr = false;
  en = false;
  esp = false;

  constructor(
    private profileService: ATMProfileService,
    private globalService: GlobalService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user1 = localStorage.getItem('user');
    this.user1 = JSON.parse(this.user1);
    this.language = this.user1.languageCode;

    if (this.language === "en") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / Profile"));
      this.en = true;
    } else if (this.language === "fr") {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / Profile"));
      this.fr = true;
    } else {
      Promise.resolve().then(() => this.globalService.titleSubject.next("Smart ATM / Profile"));
      this.esp = true;
    }

    this.loadProfiles();
  }

  loadProfiles(): void {
    this.profileService.getAllATMProfiles().subscribe(
      data => {
        this.profiles = data;
        this.updateDisplayedProfiles();
      },
      error => {
        this.errorMessage = 'Failed to load profiles.';
        this.clearMessages();
      }
    );
  }

  addProfile(): void {
    const newProfile: ATMProfile = {
      profileCode: this.profileCode,
      profileWording: this.profileWording,
      profileType: this.profileType,
    };

    this.profileService.createOrUpdateATMProfile(newProfile).pipe(
      tap(response => {
        this.successMessage = `Profile number ${newProfile.profileCode} added successfully.`;
        this.loadProfiles();
        this.resetForm();
        this.clearMessages();
      }),
      catchError(error => {
        this.errorMessage = 'Failed to add profile.';
        this.clearMessages();
        return of(); // Return an observable to handle the error case
      })
    ).subscribe();
  }

  updateProfile(): void {
    const updatedProfile: ATMProfile = {
      profileCode: this.profileCode,
      profileWording: this.profileWording,
      profileType: this.profileType,
    };

    this.profileService.createOrUpdateATMProfile(updatedProfile).pipe(
      tap(response => {
        this.successMessage = `Profile number ${updatedProfile.profileCode} updated successfully.`;
        this.loadProfiles();
        this.resetForm();
        this.clearMessages();
      }),
      catchError(error => {
        this.errorMessage = 'Failed to update profile.';
        this.clearMessages();
        return of(); // Return an observable to handle the error case
      })
    ).subscribe();
  }

  deleteProfile(profile: ATMProfile): void {
    if (!profile.profileCode) {
      this.errorMessage = 'Profile code is required to delete.';
      return;
    }

    this.profileService.deleteATMProfileByCode(profile.profileCode).subscribe(
      response => {
        console.log("response===>",response);
        
        this.successMessage = `Profile number ${profile.profileCode} deleted successfully.`;
        this.loadProfiles();
      },
      error => {
        this.errorMessage = `Profile number ${profile.profileCode} deleted successfully.`;
        this.loadProfiles();
      }
    );
  }

  editProfile(profile: ATMProfile): void {
    this.isEditing = true;
    this.profileCode = profile.profileCode;
    this.profileWording = profile.profileWording;
    this.profileType = profile.profileType;
  }

  resetForm(): void {
    this.profileCode = '';
    this.profileWording = '';
    this.profileType = '';
    this.isEditing = false;
  }

  filterProfiles(): void {
    this.updateDisplayedProfiles();
  }

  updateDisplayedProfiles(): void {
    const itemsPerPage = 5; // Number of items per page
    const filteredProfiles = this.profiles.filter(profile =>
      profile.profileCode.toLowerCase().includes(this.searchText.toLowerCase())
    );

    this.totalPages = Math.ceil(filteredProfiles.length / itemsPerPage);
    this.displayedProfiles = filteredProfiles.slice(
      (this.currentPage - 1) * itemsPerPage,
      this.currentPage * itemsPerPage
    );
  }

  goToPage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.updateDisplayedProfiles();
    }
  }

  togglePageList(): void {
    this.showPageList = !this.showPageList;
  }

  clearMessages(): void {
    setTimeout(() => {
      this.clearSuccessMessage();
      this.clearErrorMessage();
    }, 5000); // Délai de 5 secondes
  }

  clearSuccessMessage(): void {
    this.successMessage = '';
  }

  clearErrorMessage(): void {
    this.errorMessage = '';
  }
}
