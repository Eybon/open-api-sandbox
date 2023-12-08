import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Formation, FormationService } from 'src/generated-openapi';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent {

  formation: Formation | undefined;
  resultAppelService: string | undefined;

  constructor(private formationService: FormationService) { }

  getFormation(): Observable<Formation> {
    return this.formationService.getFormation(1);
  }

  getFormationFile() {
    console.log("test");
    return this.formationService.getFormationFile(1)
      .subscribe((response: Blob) => {
        saveAs(response, 'file.xml');
      }
      );
  }

  ngOnInit() {
    this.formationService.getFormation(32).subscribe({
      next: (formationResult: Formation) => {
        this.formation = formationResult;
        this.resultAppelService = 'Appel en succès !'
        console.log('Appel getFormation OK, resultat :' + JSON.stringify(this.formation));
      },
      error: (error) => {
        // do nothing
        this.resultAppelService = 'Appel KO ! F12 pour ouvrir la console de logs'
        console.log('Appel getFormation KO');
      }
    });
  }
}
