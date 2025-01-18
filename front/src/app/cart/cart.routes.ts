import {Routes} from "@angular/router";
import {ProductListComponent} from "../products/features/product-list/product-list.component";
import {CartComponent} from "./features/cart/cart.component";

export const CART_ROUTES: Routes = [
  {
    path: "",
    component: CartComponent,
  },
];
