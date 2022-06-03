package com.ecommerce.api.controllers

import com.ecommerce.api.models.Product
import com.ecommerce.api.models.ProductImageDTO
import com.ecommerce.api.service.ProductService
import org.springframework.web.bind.annotation.*
import org.springframework.http.MediaType
import javax.validation.Valid


@RestController
@RequestMapping("/product")
@CrossOrigin("https://ecommercefrontend17.herokuapp.com")
class ProductController(var productService: ProductService) {

    @PostMapping(
        value = ["/add"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addProduct(@Valid productImageDTO: ProductImageDTO): Product {
        return this.productService.addProduct(
            productImageDTO.productImage,
            productImageDTO.name,
            productImageDTO.price,
            productImageDTO.quantity,
            productImageDTO.description
        )
    }



    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): Product = this.productService.viewProductById(id)


    @GetMapping("/{name}")
    fun getAllProductByName(@PathVariable name: String): List<Product> = this.productService.viewProductByName(name)

    @GetMapping
    fun getAllProducts(): List<Product> = this.productService.viewAllProducts()

    @DeleteMapping("/{id}")
    fun deleteProductsById(@PathVariable id: String): String = this.productService.deleteProduct(id)
}