import { NestedTreeControl } from '@angular/cdk/tree';
import { Component, OnInit , Renderer2, ViewChild, ElementRef  } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { GlobalService } from 'src/app/services/global.service';
import { WebsocketService } from 'src/app/services/icc/websocket.service';
import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { EmvTagServiceService } from 'src/app/services/icc/emv-tag-service.service';
// import { DataService } from 'src\app\services\icc\websocket.service.ts';
import { saveAs } from 'file-saver';
// import * as xml2js from 'xml2js';
import { jsPDF } from 'jspdf';
import { cbTags } from 'src/app/data/tags_speciaux';
// Importez le tableau cbTags depuis votre fichier data/tag_specianx.ts



(pdfMake as any).vfs = pdfFonts.pdfMake.vfs;


@Component({
  selector: 'app-emv-card-explorer',
  templateUrl: './emv-card-explorer.component.html',
  styleUrls: ['./emv-card-explorer.component.scss']
})
export class EmvCardExplorerComponent implements OnInit {

  carddata: Node[] = [];
  tab_1: boolean = true;
  tab_2: boolean = false;
  p=0;
  cmdList:any=[]

 myForm: FormGroup;
emvTagService: any;
yourEmvTagData: any;
otherList: any[] = [];
emvTags: any[] = [];
tab_3: any[] = [];
showModal: boolean = false;
valeurInput1: string = ''; // Ajoutez cette ligne
valeurInput2: string = ''; 
valeurInput3: string = ''; 
public cbTags = cbTags;
public selectedTag: string = ''; // ou le type approprié en fonction de votre modèle de données
public checkedTags: any[] = [];



constructor(
  private dataService: EmvTagServiceService,
  private fb: FormBuilder,
  private globalService: GlobalService,
  private websocketService: WebsocketService
) {
  this.myForm = this.fb.group({
    Label: new FormControl(''),
    Length: new FormControl(''),
    Value: new FormControl(''),
    HexValue: new FormControl('')
  });
  
}


public addCheckedTags() {
  this.checkedTags = this.cbTags.filter(tag => tag.selected);
  // console.log('checkedTags:', this.checkedTags);
}

  openModal() {
    this.showModal = true;
    // Ajoutez ici la logique pour ajuster la classe CSS ou appliquer d'autres styles si nécessaire.
  }

  closeModal() {
    this.showModal = false;
  }
  
  validateModal() {
    // Ajoutez ici la logique de validation du modèle
    // Par exemple, vous pouvez traiter les valeurs des inputs ici
    // console.log("Modal validated");
  
    // Ajoutez ici l'appel à la méthode pour ajouter les éléments cochés à la liste checkedTags
    this.addCheckedTags();
  
    // Fermez le modèle après avoir ajouté les éléments cochés
    this.closeModal();
  }
  
 downloadXml() {
  // Générer le contenu XML à partir de vos données
  const xmlContent = this.generateXmlContent();

  // Créer un Blob avec le contenu XML
  const blob = new Blob([xmlContent], { type: 'application/xml' });

  // Créer un objet URL pour le Blob
  const url = window.URL.createObjectURL(blob);

  // Créer un élément <a> pour déclencher le téléchargement
  const a = document.createElement('a');
  a.href = url;
  a.download = 'donnees.xml'; // Nom du fichier à télécharger

  // Ajouter l'élément <a> au DOM et le déclencher
  document.body.appendChild(a);
  a.click();

  // Retirer l'élément <a> du DOM
  document.body.removeChild(a);

  // Libérer l'URL de l'objet Blob
  window.URL.revokeObjectURL(url);
}

generateXmlContent(): string {
  let xml = '<?xml version="1.0" encoding="UTF-8" ?>\n';
  xml += '<tags>\n';

  for (const tag of this.alltags) {
    xml += '  <node>\n';
    xml += `    <Name>${tag.Name}</Name>\n`;
    xml += `    <Value>${tag.Value || ''}</Value>\n`;
    xml += `    <Length>${tag.Length || ''}</Length>\n`;
    xml += `    <Label>${tag.Label || ''}</Label>\n`;
    xml += '  </node>\n';
  }

  xml += '</tags>';
  
  return xml;
}


downloadPDF() {
  const pdf = new jsPDF();

  const logoLeftPath = 'assets/logo.png';
  const logoRightPath = 'assets/logo.png';

  // Function to add logos
  function addLogos(yOffset: number) {
    pdf.addImage(logoLeftPath, 'PNG', 10, yOffset, 40, 30);
    pdf.addImage(logoRightPath, 'PNG', 160, yOffset, 40, 30);
  }

  // Function to add content
  function addContent(yOffset: number, isFirstPage: boolean) {
    if (isFirstPage) {
      pdf.setFont('helvetica', 'bold');
      pdf.setFontSize(12);
      pdf.setTextColor(0, 0, 0);
      pdf.text('APPLICATION TAG LIST', 20, yOffset + 15);
      // pdf.text('Application ID[A0000000032010] For Card Number [4779103001234718]', 20, yOffset + 30);
    }

    pdf.setFontSize(10);
    pdf.setTextColor(0, 0, 0);
    pdf.text('Name', 10, yOffset + 45);
    pdf.text('Label', 50, yOffset + 45);
    pdf.text('Value', 90, yOffset + 45);
  }

  let yOffset = 10; // Initial Y offset

  addLogos(yOffset); // Add logos at the top
  yOffset += 50; // Adjust Y offset to leave space for logos

  let isFirstPage = true;
  addContent(yOffset, isFirstPage); // Add content below logos for the first page
  isFirstPage = false;

  yOffset += 55; // Adjust Y offset for the content

  const maxWidth = 180; // ou une autre valeur appropriée

  this.alltags.forEach((tag: { Name: any; Label: any; Value: any; details: any[]; }) => {
    if (yOffset + 15 > 270) {
      pdf.addPage();
      addLogos(10);
      yOffset = 20;
      addContent(yOffset, isFirstPage);
      isFirstPage = false;
      yOffset += 55;
    }

    pdf.setFillColor(173, 216, 230);
    pdf.rect(5, yOffset, 200, 8, 'F');

    const rowText = `${tag.Name} -- ${tag.Label} -- ${tag.Value}`;
    const splittedText = pdf.splitTextToSize(rowText, maxWidth);
    pdf.text(splittedText, 10, yOffset + 5);

    yOffset += 15;

    if (tag.details) {
      tag.details.forEach((dtag) => {
        if (yOffset + 10 > 270) {
          pdf.addPage();
          addLogos(10);
          yOffset = 20;
          addContent(yOffset, isFirstPage);
          isFirstPage = false;
          yOffset += 55;
        }

        const maxWidthDetails = 180; // ou une autre valeur appropriée
        const splittedDetails = pdf.splitTextToSize(dtag, maxWidthDetails);
        pdf.text(splittedDetails, 20, yOffset);
        yOffset += 10;
      });
    }

    yOffset += 10;
  });

  pdf.save('table_data.pdf');
}





















saveFormDataToTable(): void {
  const newData: any = {
    tagCode: '', // Ajoutez la valeur appropriée pour tagCode
    tagName: this.myForm.value.Label || '', // Utilisez la valeur de Label pour tagName (ou autre source de données appropriée)
    tagValue: this.format === 'hex' ? this.myForm.value.Value : this.myForm.value.HexValue || '', // Utilisez les valeurs appropriées pour tagValue

    // Assurez-vous d'ajouter les valeurs appropriées pour les autres propriétés de Node
    Length: '',
    Label: '',
    Name: '',
    Value: '',
  };

  this.carddata.push(newData);
  this.dataSource.data = this.carddata;
}


saveData() {
  const dataToSave = this.dataSource.data;

  this.dataService.saveDataToBackend(dataToSave).subscribe(
    (response: any) => { // Spécifier le type 'any' pour la réponse
      // console.log('Données sauvegardées avec succès !', response);
      // Réinitialiser ou effectuer toute autre action après la sauvegarde réussie
    },
    (error: any) => { // Spécifier le type 'any' pour l'erreur
      console.error('Erreur lors de la sauvegarde des données :', error);
      // Gérer les erreurs ou afficher des messages d'erreur
    }
  );
}


showDetails(node: Node) {
  // console.log("details : ", node);
  this.myForm.patchValue({...node, HexValue: this.hex_to_ascii(node.Value)});
  
  // Vérifier si le nœud est de type Node avant de l'ajouter à this.carddata
  if (node instanceof Object && 'Name' in node && 'Value' in node && 'Length' in node && 'Label' in node) {
    // Vérifier si le nœud avec le même Name n'existe pas déjà dans this.carddata
    const existingNodeIndex = this.carddata.findIndex(item => item.Name === node.Name);
    if (existingNodeIndex === -1) {
      const newData: any = {
        Name: node.Name,
        Value: node.Value,
        Length: node.Length,
        Label: node.Label,
        HexValue: this.hex_to_ascii(node.Value), // Assigner la valeur HexValue
        AsciiValue: this.convertToAscii(node.Value) // Convertir la valeur en ASCII
        ,
        tagCode: '',
        tagName: '',
        tagValue: ''
      };
      this.carddata.push(newData);
 
      // console.log(this.myForm.value);
    } else {
    
      this.carddata[existingNodeIndex].Value = node.Value;
      this.carddata[existingNodeIndex].Length = node.Length;
      this.carddata[existingNodeIndex].Label = node.Label;
      this.carddata[existingNodeIndex].HexValue = this.hex_to_ascii(node.Value);
      this.carddata[existingNodeIndex].AsciiValue = this.convertToAscii(node.Value);
      
    
    }
  }
}

open_tab(tab: number): void {
    this.p=0
    switch (tab) {
      case 1:
        this.tab_1 = true;
        this.tab_2 = false;
        break;
      case 2:
        this.tab_2 = true;
        this.tab_1 = false;
        break;
    }
  }

  alltags: any
  senddata(): void {
    this.showModal = false;
    this.readSmartCardData();
  }

  hasChild = (_: number, node: Node) => !!node.ChildNodes && node.ChildNodes.length > 0;
  show: boolean = false;

  smartCardDataList: any[] = [];

  readSmartCardData(): void {
    this.websocketService.send({ header: 'readSmartCard', messageOut: JSON.stringify(this.checkedTags) });
    this.websocketService.subscribeToSmartCardData((smartCardData) => {
      const parsedData = JSON.parse(smartCardData) as Node;
      // console.log("parsedData ",parsedData)
  
      // Vérifier si le nœud avec le même Name n'existe pas déjà dans this.carddata
      const existingNodeIndex = this.carddata.findIndex(item => item.Name === parsedData.Name);
      if (existingNodeIndex === -1) {
        // Ajouter le nouveau nœud à this.carddata
        this.carddata.push(parsedData);
  
        // Mettre à jour la source de données de l'arborescence avec this.carddata
        this.dataSource.data = this.carddata;
        this.show = true;
      }
    });
    this.websocketService.subscribeToSmartCardCommande((smartCardData) => {
      // console.log("parsedData ",smartCardData)
      this.cmdList = smartCardData as [Command];
      // console.log("parsedData ",this.cmdList)
    });

    this.websocketService.subscribeToSmartCardTags((smartCardData) => {
      // console.log("tags  ",smartCardData)
      this.alltags = smartCardData as [any];
      // console.log("tags  ",this.cmdList)
    });
  }
///////////////////////////////////////////////////////////////////

  
  convertFromHex(hexString: string): string {
    const bytes = this.hexToBytes(hexString);
    return this.bytesToString(bytes);
  }
  
  hexToBytes(hexString: string): number[] {
    const bytes = [];
    for (let i = 0; i < hexString.length; i += 2) {
      bytes.push(parseInt(hexString.substr(i, 2), 16));
    }
    return bytes;
  }
  
  bytesToString(bytes: number[]): string {
    return String.fromCharCode.apply(null, bytes);
  }



  //////////////////////////////////////////////////////////////////////////////////////////////////
 
  // Méthode pour convertir les données de commandes en XML
  convertCommandsToXml(commandsData: Command[]): string {
    let xmlContent = '<?xml version="1.0" encoding="UTF-8"?>\n<commands>\n'; // En-tête XML et ouverture de la balise racine

    for (const command of commandsData) {
      xmlContent += `<command>\n`;
      xmlContent += `<Class>${command.Class}</Class>\n`;
      xmlContent += `<Ins>${command.Ins || ''}</Ins>\n`;
      xmlContent += `<P1>${command.P1 || ''}</P1>\n`;
      xmlContent += `<P2>${command.P2 || ''}</P2>\n`;
      xmlContent += `<Lc>${command.Lc || ''}</Lc>\n`;
      xmlContent += `<Data>${command.Data || ''}</Data>\n`;
      xmlContent += `<Le>${command.Le || ''}</Le>\n`;
      xmlContent += `<Result>${command.Result || ''}</Result>\n`;
      // Ajoutez d'autres balises selon vos données si nécessaire
      xmlContent += `</command>\n`;
    }

    xmlContent += '</commands>'; // Fermeture de la balise racine

    return xmlContent;
  }

  // Méthode pour générer le fichier XML des commandes et le télécharger
  saveCommandsToXml(): void {
    const commandsXml = this.convertCommandsToXml(this.cmdList); // Convertir les données de commandes en XML

    const blob = new Blob([commandsXml], { type: 'application/xml' }); // Créer un Blob avec le contenu XML
    const url = URL.createObjectURL(blob); // Créer une URL pour le Blob

    const link = document.createElement('a');
    link.href = url;
    link.download = 'commands.xml'; // Nom du fichier téléchargé pour les commandes
    document.body.appendChild(link);

    // Simuler un clic sur le lien pour déclencher le téléchargement
    link.click();

    // Nettoyer après le téléchargement
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  }

  
  convertToAscii(hexString: string | null): string {
    if (!hexString) {
      return "";
    } else {
      let asciiString = '';
      for (let i = 0; i < hexString.length; i += 2) {
        const byte = parseInt(hexString.substr(i, 2), 16);
        asciiString += String.fromCharCode(byte);
      }
      return asciiString;
    }
  }

  clearData(): void {
    this.carddata = []; // Réinitialisez les données de la carte EMV
    this.dataSource.data = this.carddata; // Mettez à jour la source de données de l'arborescence
    this.cmdList = []; // Réinitialisez la liste des commandes
    this.tab_1 = false; // Réinitialisez l'état de l'onglet 1
    this.tab_2 = false; // Réinitialisez l'état de l'onglet 2
    this.myForm.reset(); // Réinitialisez le formulaire
    this.p = 0; // Réinitialisez la pagination
    this.show = false; // Cachez l'arborescence
  }

  // convertToXml(data: Node[]): string {
 
  //   const xmlHeader = '<?xml version="1.0" encoding="UTF-8"?>';
  //   let xmlContent = xmlHeader + '<root>'; // Structure XML de base

  //   // Convertir les données en XML
  //   for (const node of data) {
  //     xmlContent += `<node>
  //       <Name>${node.Name}</Name>
  //       <Value>${node.Value}</Value>
  //       <!-- Ajoutez d'autres balises pour d'autres propriétés -->
  //     </node>`;
  //   }

  //   xmlContent += '</root>'; // Fermeture de la structure XML

  //   return xmlContent;
  // }

  // exportToXml(): void {
  //   const xmlContent = this.convertToXml(this.carddata); // Convertir les données en XML

  //   const blob = new Blob([xmlContent], { type: 'application/xml' }); // Créer un Blob avec le contenu XML
  //   const url = URL.createObjectURL(blob); // Créer une URL pour le Blob

  //   const link = document.createElement('a');
  //   link.href = url;
  //   link.download = 'emv_data.xml'; // Nom du fichier téléchargé
  //   document.body.appendChild(link);

  //   // Simuler un clic sur le lien pour déclencher le téléchargement
  //   link.click();

  //   // Nettoyer après le téléchargement
  //   document.body.removeChild(link);
  //   URL.revokeObjectURL(url);
  // }


  treeControl = new NestedTreeControl<Node>(node => node.ChildNodes);
  dataSource = new MatTreeNestedDataSource<Node>();
  dsCommand = new MatTreeNestedDataSource<Command>();



  
  ngOnInit(): void {

    
    this.globalService.titleSubject.next("Smart Card / Card Explorer");
  

  }
 
 hex_to_ascii(str1: string | null) {
    if (!str1) {
      return "";
    } else {
      let asciiString = '';
      for (let i = 0; i < str1.length; i += 2) {
        const byte = parseInt(str1.substr(i, 2), 16);
        asciiString += String.fromCharCode(byte);
      }
    
      return asciiString;
    }
 }


 downloadTableAsPDF(): void {
  const bodyData = this.carddata.map((node: Node) => [node.Name || '', node.Value || '', node.Length || '', node.Label || '']);
  const documentDefinition = {
      content: [
          {
              table: {
                  headerRows: 1,
                  widths: ['*', '*', '*', '*'],
                  body: [
                      ['Name', 'Value', 'Length', 'Label'],
                      ...bodyData
                  ]
              }
          }
      ]
  };

  pdfMake.createPdf(documentDefinition).download('table_data.pdf');
}
  

///////////////   SAVE PDF ////////////////


///////////////////////////

format: string = "hex";
  onFormatChange(event: Event) {
    this.format = (event.target as HTMLInputElement).value;
  }
}

interface Node {
  Name: string;
  Value: string | null;
  Length: string | null;
  Label: string | null;
  ChildNodes?: Node[];
  HexValue?: string | null; 
  AsciiValue?: string | null; 
}
interface Command {
  Class: string;
  Data: string | null;
  Ins: string | null;
  Lc: string | null;
  Le: string | null;
  P1: string | null; 
  P2: string | null; 
  Result: string | null; 
}



interface Tag {
  Name: string;
  Label?: string;
  Value?: string;
  details?: string[]; // Ou le type approprié pour vos détails
}

export interface CardModel {
  value: string;
  label: string;
  name: string;
}