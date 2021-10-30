import {NgModule} from "@angular/core";
import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";

@NgModule ({
  imports: [
    MatCardModule
  ],
  exports: [
    MatCardModule,
    MatToolbarModule,
    MatButtonModule
  ]
})
export class MaterialModule {}
