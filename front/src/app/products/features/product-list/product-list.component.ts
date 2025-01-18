import {Component, OnInit, inject, signal, computed} from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import {CartService} from "../../../cart/data-access/cart.service";
import {CommonModule} from "@angular/common";
import {PaginatorModule, PaginatorState} from "primeng/paginator";

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, CommonModule, PaginatorModule],
})
export class ProductListComponent implements OnInit {

  pageSize = signal<number>(10);
  pageNumber = signal<number>(0);
  totalItems = computed(() => this.products().length)

  private readonly productsService = inject(ProductsService);
  private readonly cartService = inject(CartService);
  public readonly products = this.productsService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  constructor() {
  }

  ngOnInit() {
    this.productsService.get().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  public addToCart(product : Product){
    this.cartService.addProduct(product);
    if(product.quantity){
      product.quantity=product.quantity-1;
      this.productsService.update(product)
    }
  }

  paginationProducts = computed(() => {
    const startIndex = this.pageNumber();
    const endIndex = startIndex + this.pageSize();
    console.log()
    return this.products().slice(startIndex, endIndex);
  });

  onPageChange($event: PaginatorState) {
    this.pageNumber.set($event.first || 0);
    this.pageSize.set($event.rows || 10);
  }

}
