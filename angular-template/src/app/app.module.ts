import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { environment } from 'src/environments/environment';
import { AppComponent } from './app.component';
import { ApiModule, Configuration, ConfigurationParameters, } from 'src/generated-openapi';
import { HttpClientModule } from '@angular/common/http';
import { FormationComponent } from './formation/formation.component';
import { MatCardModule } from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InfoTechComponent } from './info-tech/info-tech.component';

export function apiConfigFactory(): Configuration {
  const params: ConfigurationParameters = {
    basePath: environment.formationApiBasePath,
  };
  return new Configuration(params);
}

@NgModule({
  imports: [
    BrowserModule,
    ApiModule,
    HttpClientModule,
    ApiModule.forRoot(apiConfigFactory),
    MatCardModule,
    MatButtonModule,
    BrowserAnimationsModule
  ],
  declarations: [
    AppComponent,
    FormationComponent,
    InfoTechComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
