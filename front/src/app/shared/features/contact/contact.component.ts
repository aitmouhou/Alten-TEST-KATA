import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Button} from "primeng/button";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    Button
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {

  contactFg: FormGroup;

  constructor(private fb :FormBuilder) {
    this.contactFg= this.fb.group({
      email:[null, [Validators.required, Validators.email]],
      message:[null,[Validators.required, Validators.maxLength(300)]]
    })
  }

  onSubmit() {
    if (this.contactFg.valid) {
      console.log('Formulaire soumis :', this.contactFg.value);
      alert('Votre message a été envoyé avec succès !');
    } else {
      alert('Veuillez remplir correctement le formulaire.');
    }
  }

}
