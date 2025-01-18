import {computed, Injectable, signal} from '@angular/core';
import {Product} from "../../products/data-access/product.model";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public readonly  cartProducts = signal<Product[]>([]);
  cartProductsCount = computed(() => this.cartProducts().length);

  addProduct(product: Product) {
    console.log(product);
    this.cartProducts.update(products => [...products, product]);
    console.log(this.cartProducts.asReadonly());
  }

  removeProduct(productId: number) {
    this.cartProducts.update(products => products.filter(p => p.id !== productId));
  }

  constructor() { }

  getCartProductsCount() {
    return this.cartProductsCount;
  }
}


