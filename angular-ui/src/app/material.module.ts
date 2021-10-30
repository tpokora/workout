import {NgModule} from "@angular/core";
import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";

@NgModule ({
  imports: [
    MatCardModule
  ],
  exports: [
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    MatListModule
  ]
})
export class MaterialModule {}
