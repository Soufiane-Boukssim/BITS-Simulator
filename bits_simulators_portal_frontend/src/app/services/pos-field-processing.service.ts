import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { environment } from 'src/env/environement';

const baseUrl = environment.url
@Injectable({
  providedIn: 'root'
})
export class PosFieldProcessingService {
  constructor(private http: HttpClient) {
  }

  retrieveSubFields(fieldId: string): Observable<any> {
    return this.http.post(`${baseUrl}/su/pos/subFldDef/getAllSubfldDefinitions`, {})
      .pipe(
        map((data: any) => {
          // console.log("----------- sub fields ------------");
          // console.log(data);
          // console.log("----------- sub fields ------------");

          if (data.respCode === "000") {
            // Filter subfields based on fieldId
            const filteredSubFields = fieldId ? data.result.filter((subfield: any) => subfield.subfldType === fieldId) : data.result;
            return { ...data, result: filteredSubFields };
          } else {
            // console.log(data.respMsg);
            return data;
          }
        })
      );
  }

  // retrieveSubFields(fieldId: string): void {
  //   this.http.post(baseUrl + '/su/pos/subFldDef/getAllSubfldDefinitions', {}).subscribe({
  //     next: (data: any) => {
  //       // console.log("----------- sub fields ------------");
  //       // console.log(data);
  //       // console.log("----------- sub fields ------------");

  //       if (data.respCode === "000") {
  //         this.subFields = data.result.filter((subfield: any) => subfield.subfldType === fieldId);
  //       } else {
  //         // console.log(data.respMsg);
  //       }
  //     },
  //     error: (error) => {
  //       // console.log(error);
  //     }
  //   });
  // }

  subFields: any;

  processFields(fields: any[]): any[] {
    const processedFields: any[] = [];

    for (const field of fields) {
      const fieldCode = field.fieldDef.id.fieldId;
      switch (fieldCode) {
        case 3:
          processedFields.push(this.processFieldCode3(field));
          break;
        case 22:
          processedFields.push(this.processFieldCode22(field));
          break;
        case 35:
          processedFields.push(this.processFieldCode35(field));
          break;
        case 54:
          processedFields.push(this.processFieldCode54(field));
          break;
        case 53:
          processedFields.push(this.processFieldCode53(field));
          break;
        case 48:
          processedFields.push(this.processFieldCode48(field));
          break;
        default:
          processedFields.push(field);
          break;
      }
    }
    return processedFields;
  }

  private processFieldCode3(field: any): any {
    const value = field.value;
    if (value && value.length >= 6) {
      return {
        ...field,
        collapsed: true,
        details: [
          { name: 'Processing Code', value: value.substring(0, 2) },
          { name: 'Source Account', value: value.substring(2, 4) },
          { name: 'Destination Account', value: value.substring(4, 6) },
        ],
      };
    }
    return field;
  }

  private processFieldCode22(field: any): any {
    const value = field.value;
    const details = [];

    if (value && value.length >= 12) {
      details.push({ name: 'Capacité d’entrée de données de carte', value: value.charAt(0) });
      details.push({ name: 'Capacité d’authentification du titulaire', value: value.charAt(1) });
      details.push({ name: 'Capacité de rétention de carte', value: value.charAt(2) });
      details.push({ name: 'Environnement fonctionnel', value: value.charAt(3) });
      details.push({ name: 'Présence du titulaire de la carte', value: value.charAt(4) });
      details.push({ name: 'Présence de la carte', value: value.charAt(5) });
      details.push({ name: 'Mode d’entrée des données de carte', value: value.charAt(6) });
      details.push({ name: 'Méthode d’authentification du titulaire', value: value.charAt(7) });
      details.push({ name: 'Entité d’authentification du titulaire', value: value.charAt(8) });
      details.push({ name: 'Capacité de mise à jour', value: value.charAt(9) });
      details.push({ name: 'Capacité de sortie du terminal', value: value.charAt(10) });
      details.push({ name: 'Longueur du PIN', value: value.charAt(11) });

      field.collapsed = true;
      field.details = details;
    }
    return field;
  }


  private processFieldCode35(field: any): any {
    const value = field.value;
    const separatorIndex = value.indexOf('=') !== -1 ? value.indexOf('=') : value.indexOf('d');

    if (separatorIndex !== -1) {
      return {
        ...field,
        collapsed: true,
        details: [
          { name: 'Card Number', value: value.substring(0, separatorIndex) },
          { name: 'Expiry date', value: value.substring(separatorIndex + 1, separatorIndex + 5) },
          { name: 'SSS', value: value.substring(separatorIndex + 5, separatorIndex + 8) },
          { name: 'ADITIONAL DATA', value: value.substring(separatorIndex + 8) },
        ],
      };
    }
    return field;
  }

  private processFieldCode54(field: any): any {
    const value = field.value;
    const details = [];

    if (value && (value.length >= 20)) {
      for (let i = 0; i < value.length; i += 20) {
        details.push({ name: `Type compte${i / 20 + 1}`, value: value.substring(i, i + 2) });
        details.push({ name: `Type montant${i / 20 + 1}`, value: value.substring(i + 2, i + 4) });
        details.push({ name: `Code Monnaie${i / 20 + 1}`, value: value.substring(i + 4, i + 6) });
        details.push({ name: `Crédit/Débit${i / 20 + 1}`, value: value.substring(i + 6, i + 7) });
        details.push({ name: `Montant Balance${i / 20 + 1}`, value: value.substring(i + 7, i + 19) });
      }

      field.collapsed = true;
      field.details = details;
    }

    return field;
  }

  private processFieldCode53(field: any): any {
    const value = field.value;
    const details = [];

    if (value && value.length >= 6) {
      details.push({ name: 'Format de sécurité (Pin block encryption method)', value: value.substring(0, 2) });
      details.push({ name: 'Format du pin block', value: value.substring(2, 4) });
      details.push({ name: 'Index de la clé d\'encryption du PIN', value: value.substring(4, 6) });
    }

    return {
      ...field,
      collapsed: true,
      details: details,
    };
  }

  private processFieldCode48(field: any): any {
    // console.log(field)
    const value = field.value;
    const details = [];

    let index = 0;
    while (index + 6 <= value.length) {
      const tag = value.substring(index, index + 3);
      // console.log(tag)
      const length = parseInt(value.substring(index + 3, index + 6), 10);
      // console.log(length)

      if (index + 6 + length <= value.length) {
        // console.log(true)
        const tagValue = value.substring(index + 6, index + 6 + length);
        details.push({ name: tag, value: tagValue });
        index += 6 + length;
      } else {
        break;
      }
    }
    // console.log(details)
    return {
      ...field,
      collapsed: true,
      details: details,
    };
  }
}
