import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';
import { NdcDisplayLayoutService } from '../../../services/ndc-display-layout.service';
import { NdcDisplayLayout } from '../../../models/ndc-display-layout.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.scss']
})
export class ScreenComponent {
  isEditing: boolean = false;
  isViewingDetails: boolean = false; // Ajout d'un indicateur pour "View Details"
  layouts: NdcDisplayLayout[] = [];
  activeSection: string = 'layout'; // Default section
  displayCarrierName: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';
  // Initialize with default values
  layout: NdcDisplayLayout = {
    id: {
      profileCode: '',
      screenNumber: ''
    },
    screenName: '',
    screenLanguage: '',
    voiceNumber: '',
    numberConcatenation: 0,
    track1Name: false,
    lineNumber: 0,
    columnNumber: 0,
    fgColour: '',
    bgColour: '',
    blinking: '',
    policeNumber: '',
    formFeedFlag: '',
    currentPositionX: '',
    currentPositionY: '',
    currentFgColour: '',
    currentBgColour: '',
    currentBlinking: '',
    currentPolice: ''
  };
  selectedLayout: NdcDisplayLayout | null = null;
  
  textObjectItems: any[] = [{ objet: 'image', line: 0, colonne: 0, numeroObjet: 0 }];
  screens: any[] = [{ name: 'desc', order: 0, time: 0 }];

  constructor(private layoutService: NdcDisplayLayoutService) {}

  ngOnInit(): void {
    this.getAllLayouts(); // Appelle getAllLayouts lorsque le composant est initialisé
    // this.fetchLayouts();
  }

  showSection(section: string) {
    this.activeSection = section;
  }

  toggleCarrierName(event: any) {
    this.displayCarrierName = event.target.checked;
  }


  addTextObjectRow() {
    this.textObjectItems.push({ objet: 'image', line: 0, colonne: 0, numeroObjet: 0 });
  }

  deleteAllTextObjectRow() {
    this.textObjectItems = [];
  }

  
  addRow() {
    this.screens.push({ name: 'desc', order: 0, time: 0 });
  }

  deleteAll() {
    this.screens = [];
  }

  
  createLayout(layout: NdcDisplayLayout): void {
    // Vérifier que tous les champs nécessaires sont remplis
    if (!layout.id.profileCode || !layout.id.screenNumber || !layout.screenName || !layout.screenLanguage || 
      
      !layout.policeNumber || layout.lineNumber === undefined || layout.columnNumber === undefined || !layout.fgColour || !layout.bgColour || 
      
      !layout.voiceNumber || !layout.numberConcatenation || 
        // layout.track1Name === undefined || 
        // !layout.blinking || //!layout.formFeedFlag || 
        //!layout.currentPositionX || !layout.currentPositionY || 
        
        !layout.currentFgColour || //!layout.currentBgColour || 

        !layout.currentBlinking) //!layout.currentPolice 
        
        {
      console.error('All fields must be filled.');
      this.errorMessage = 'All fields must be filled.';
      return;
    }
  
    this.layoutService.createLayout(layout).subscribe(
      response => {
        console.log('Layout created successfully:', response);
        this.successMessage = `Layout created successfully with id: profileCode=${layout.id.profileCode} & screenNumber=${layout.id.screenNumber}`;
        this.getAllLayouts();
      },
      error => {
        console.error('Error creating layout:', error);
        this.errorMessage = `Error creating layout with id: profileCode=${layout.id.profileCode} & screenNumber=${layout.id.screenNumber}`;
      }
    );
  }
  

  







  getAllLayouts(): void {
    this.layoutService.getAllLayouts().subscribe(
      (data) => {
        this.layouts = data; // Store all layouts
        this.applyFilters(); // Apply filters immediately after getting data
      },
      (error) => {
        console.error('Erreur lors de la récupération des données:', error);
      }
    );
  }
  
  applyFilters(): void {
    this.filteredLayouts = this.layouts.filter(layout => {
      const profileMatch = !this.profileFilter || layout.id.profileCode.toLowerCase().includes(this.profileFilter.toLowerCase());
      const numeroMatch = !this.numeroFilter || layout.id.screenNumber.toLowerCase().includes(this.numeroFilter.toLowerCase());
      return profileMatch && numeroMatch;
    });
    //test
    this.totalPages = Math.ceil(this.filteredLayouts.length / this.itemsPerPage);
    this.goToPage(1); // Reset to the first page after applying filters
  }














  deleteLayout(profileCode: string, screenNumber: string) {
    this.layoutService.deleteLayout(profileCode, screenNumber).subscribe(
      () => {
        console.log(`Layout deleted successfully with id: {profileCode=${profileCode} & screenNumber=${screenNumber}}`);
        this.successMessage = `Layout deleted successfully with id: {profileCode=${profileCode} & screenNumber=${screenNumber}}`;
        this.getAllLayouts(); // Recharger la liste des layouts après suppression
      },
      (error: HttpErrorResponse) => {
        console.error(`Error deleting layout with id: {profileCode=${profileCode} & screenNumber=${screenNumber}}`, error);
        
        // Gestion de l'erreur
        if (error.error && error.error.respCode) {
          this.errorMessage = `Error deleting layout with respCode: ${error.error.respCode}`;
        } else {
          this.errorMessage = `Error deleting layout with id: {profileCode=${profileCode} & screenNumber=${screenNumber}}`;
        }
      }
    );
  }

  editLayout(screen: NdcDisplayLayout): void {
    this.isEditing = true; // Active le mode édition
    this.isViewingDetails = false; // Désactive le mode de visualisation
    this.layout.id.profileCode= screen.id.profileCode;
    this.layout.id.screenNumber= screen.id.screenNumber;
    this.layout.screenName =screen.screenName;
    this.layout.screenLanguage =screen.screenLanguage;
    this.layout.voiceNumber =screen.voiceNumber;
    this.layout.numberConcatenation =screen.numberConcatenation;
    this.layout.track1Name =screen.track1Name;
    this.layout.lineNumber =screen.lineNumber;
    this.layout.columnNumber =screen.columnNumber;
    this.layout.fgColour =screen.fgColour;
    this.layout.bgColour =screen.bgColour;
    this.layout.blinking =screen.blinking;
    this.layout.policeNumber =screen.policeNumber;
    this.layout.formFeedFlag =screen.formFeedFlag;
    this.layout.currentPositionX =screen.currentPositionX;
    this.layout.currentPositionY =screen.currentPositionY;
    this.layout.currentFgColour =screen.currentFgColour;
    this.layout.currentBgColour =screen.currentBgColour;
    this.layout.currentBlinking =screen.currentBlinking;
    this.layout.currentPolice =screen.currentPolice;
  }
    

  viewDetails(screen: NdcDisplayLayout): void {
    this.isViewingDetails = true; // Change to viewing details mode
    this.isEditing = false; // Désactiver le mode édition
    this.layout.id.profileCode= screen.id.profileCode;
    this.layout.id.screenNumber= screen.id.screenNumber;
    this.layout.screenName =screen.screenName;
    this.layout.screenLanguage =screen.screenLanguage;
    this.layout.voiceNumber =screen.voiceNumber;
    this.layout.numberConcatenation =screen.numberConcatenation;
    this.layout.track1Name =screen.track1Name;
    this.layout.lineNumber =screen.lineNumber;
    this.layout.columnNumber =screen.columnNumber;
    this.layout.fgColour =screen.fgColour;
    this.layout.bgColour =screen.bgColour;
    this.layout.blinking =screen.blinking;
    this.layout.policeNumber =screen.policeNumber;
    this.layout.formFeedFlag =screen.formFeedFlag;
    this.layout.currentPositionX =screen.currentPositionX;
    this.layout.currentPositionY =screen.currentPositionY;
    this.layout.currentFgColour =screen.currentFgColour;
    this.layout.currentBgColour =screen.currentBgColour;
    this.layout.currentBlinking =screen.currentBlinking;
    this.layout.currentPolice =screen.currentPolice;
  }

  updateLayout(): void {
    if (!this.layout.id.profileCode || !this.layout.id.screenNumber) {
      console.error('Profile code or screen number is missing.');
      return;
    }

    this.layoutService.updateLayout(
      this.layout.id.profileCode,
      this.layout.id.screenNumber,
      this.layout
    ).subscribe(
      (response) => {
        console.log('Layout updated successfully:', response);
        this.successMessage = `Layout updated successfully with id: profileCode=${this.layout.id.profileCode} & screenNumber=${this.layout.id.screenNumber}`;
        this.isEditing = false; // Désactive le mode édition après la mise à jour
        this.getAllLayouts(); // Recharger les données
      },
      (error) => {
        console.error('Error updating layout:', error);
        this.errorMessage = `Error updating layout with id: profileCode=${this.layout.id.profileCode} & screenNumber=${this.layout.id.screenNumber}`;
      }
    );
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
  

  cancelEdit(): void {
    this.isEditing = false;
    this.isViewingDetails = false; // Réinitialise l'indicateur pour "View Details"
    this.layout = this.getDefaultLayout(); // Réinitialise l'objet layout
  }

  getDefaultLayout(): NdcDisplayLayout {
    return {
      id: {
        profileCode: '',
        screenNumber: ''
      },
      screenName: '',
      screenLanguage: '',
      voiceNumber: '',
      numberConcatenation: 0,
      track1Name: false,
      lineNumber: 0,
      columnNumber: 0,
      fgColour: '',
      bgColour: '',
      blinking: '',
      policeNumber: '',
      formFeedFlag: '',
      currentPositionX: '',
      currentPositionY: '',
      currentFgColour: '',
      currentBgColour: '',
      currentBlinking: '',
      currentPolice: ''
    };
  }





/////////////////////////////////////////////////////////////////////////

  filteredLayouts: NdcDisplayLayout[] = []; // Layouts after applying filters
  displayedLayouts: NdcDisplayLayout[] = []; // Layouts to display on the current page

  profileFilter: string = '';
  numeroFilter: string = '';

  currentPage: number = 1;
  itemsPerPage: number = 5; // Number of items per page
  totalPages: number = 1; // Total number of pages
  showPageList: boolean = false;


  goToPage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.updateDisplayedLayout();
    }
  }

  togglePageList(): void {
    this.showPageList = !this.showPageList;
  }

  updateDisplayedLayout(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedLayouts = this.filteredLayouts.slice(startIndex, endIndex);
  }


}