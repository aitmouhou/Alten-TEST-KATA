import {Component, inject} from '@angular/core';
import {ToolbarModule} from "primeng/toolbar";
import {Router} from "@angular/router";
import {CartService} from "../../../cart/data-access/cart.service";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-navbar',
  standalone: true,
    imports: [CommonModule,
        ToolbarModule
    ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  title = "ALTEN SHOP";
  private cartService = inject(CartService);
  cartProductsCount = this.cartService.cartProductsCount;
  constructor(private router:Router) {
  }

  toggleProductList() {
    this.router.navigate(['/cart'])
  }


}
