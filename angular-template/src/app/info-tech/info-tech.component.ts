import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-info-tech',
  templateUrl: './info-tech.component.html',
  styleUrls: ['./info-tech.component.css']
})
export class InfoTechComponent {
  environment = environment;
}
