package com.ecommerce.api.service

import com.ecommerce.api.DAO.*
import com.ecommerce.api.exception.DeleteException
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.*
import org.springframework.stereotype.Service


@Service
class ProductAddedToCartServiceImpl(

    var productAddedToCartDAO: ProductAddedToCartDAO,
    var cartDAO: CartDAO,
    var orderDAO: OrderHistoryDAO,
    var userDAO : UserDAO,
    var cartService: CartService,
    var orderHistoryService: OrderHistoryService,
    var userService: UserService,

    var productDAO: ProductDAO,
//    var cartService: CartService,
    var productService: ProductService,


    ) : ProductAddedToCartService {
    override fun addCart(productID: String, tempProduct: MutableList<TempProduct>?): Cart {

        var productId = productID


        var cart : Cart = cartDAO.findById(productId).get()
        var order : OrderHistory = orderDAO.findById(productId).get()
        //var user: User = userService.getUserById(productID)

        var productTotalPay: Double = 0.0
        var productCount = 0

        for (tempProductId in tempProduct!!) {
            var prodID = tempProductId.objectid
            var product: Product? = this.productService.viewProductById(prodID)
            var reqQty: Int = tempProductId.reqQty
            product?.quantity = product?.quantity?.minus(reqQty)!!
            var price: Double? = product.price.times(reqQty)
            productTotalPay = productTotalPay.plus(price!!)
            productCount = productCount.plus(1)
            var productAddedToCart = ProductAddedToCart()
            productAddedToCart.id = prodID
            productAddedToCart.productName = product.name
            productAddedToCart.productQty = reqQty
            productAddedToCart.productPrice = price
            productAddedToCartDAO.save(productAddedToCart)
            productDAO.save(product)


            cart.products?.add(ProductAddedToCart(prodID, product.name, reqQty, price))
            order.products?.add(ProductAddedToCart(prodID,product.name,reqQty,price))

        }
         cart.totalPay = productTotalPay
         cart.count = productCount
          cart.agree = "Pending"
        //order.name = user.name
        order.deliveryStatus="Pending"
        //user.orderHistory?.add(order)
       // userDAO.save(user)


        orderDAO.save(order)
        cartDAO.save(cart)
        return cart
    }

    override fun getCartByProducts(id :String): List<ProductAddedToCart>?
    {
        var cart : Cart = cartDAO.findById(id).get()
        return cart.products?.toList()
    }

    override fun deleteProductById(orderId: String, productId: String,): String {
        val cartTemp = this.cartDAO.findById(orderId).get()
        val productTemp = this.productAddedToCartDAO.findById(productId).get()
        val index = cartTemp.products?.indexOf(productTemp)
        println(index)

//        cartTemp.products?.remove(productTemp)
        return if (index != null) {
            cartTemp.products?.removeAt(index)
            "delete successfully"
        }else{
            "delete unsuccessfully"
        }

    }

//
    //
//override fun getJobPosted(recruiterEmail: String): List<Job>? {
//    return if (recruiterDAO.existsByRecruiterEmail(recruiterEmail)) {
//        val recruiterTemp: Recruiter = this.recruiterDAO.findById(recruiterEmail).get()
//        recruiterTemp.jobsPosted?.toList()
//    } else {
//        throw GetException(" you haven't posted a job yet")
//    }
//}


//    override fun getAllCart(): List<ProductAddedToCart> {
//        if (productAddedToCartDAO.count() > 0) {
//            return this.productAddedToCartDAO.findAll()
//        } else {
//            throw GetException("Empty List")
//        }



   // }

    override fun getCart(): List<Cart> {
        if (cartDAO.count() > 0) {
            return this.cartDAO.findAll()
        } else {
            throw GetException("Empty List")
        }



    }

    override fun getHistory(): List<OrderHistory> {
        if (orderDAO.count() > 0) {
            return this.orderDAO.findAll()
        } else {
            throw GetException("Empty List")
        }
    }

    override fun deleteProduct(id: String): String {
        if (productAddedToCartDAO.existsById(id)) {
            this.productAddedToCartDAO.deleteById(id)
            return "Deleted Successfully"
        } else throw DeleteException("Product does not exists...")

    }





}