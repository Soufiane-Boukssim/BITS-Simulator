import { Pipe, PipeTransform } from '@angular/core';
import { FieldProcessingService } from '../services/field-processing.service';
import { Observable, catchError, map, of } from 'rxjs';

@Pipe({
  name: 'subFieldName'
})
export class SubFieldNamePipe implements PipeTransform {

  constructor(private fieldProcessingService: FieldProcessingService) { }

  transform(value: any, args?: any): Observable<string> {
    // console.log("entry value " + value);

    if (!value) {
      return of(value);
    }

    const body={
      "bankCode":"00100",
      "subfldProtocole":"SS"
    }

    return this.fieldProcessingService.retrieveSubFields("",body).pipe(
      map((data: any) => {
        const subFields = data.result;
        const matchingSubField = subFields.find((subField: any) => subField.subfldDefinitionId.subfldId === value);

        if (matchingSubField) {
          // console.log("res value ", value + matchingSubField.subfldDescr);
          return value + " - " + matchingSubField.subfldDescr;
        } else {
          // console.log("res value " + value);
          return value;
        }
      }),
      catchError(error => {
        // console.log(error);
        return of(value);
      })
    );
  }
}