import { Component, Injector, Input } from '@angular/core';
import { FormationService, Formation } from 'src/generated-openapi'
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-application-template';
  console = console;
  environment = environment;
}
