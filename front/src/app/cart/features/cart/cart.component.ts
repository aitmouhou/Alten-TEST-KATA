import {Component, OnInit} from '@angular/core';
import {CartService} from "../../data-access/cart.service";
import {Product} from "../../../products/data-access/product.model";
import {Button} from "primeng/button";
import {TableModule} from "primeng/table";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    Button,
    TableModule
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  public readonly products = this.cartService.cartProducts;
  constructor(private cartService:CartService) {
  }

  ngOnInit(): void {
    console.log(this.products()); // Vérifiez les produits dans la console
  }

  addProduct(product:Product) {
    this.cartService.addProduct(product);
  }

  removeProduct(productId:number) {
    this.cartService.removeProduct(productId);
  }


}
