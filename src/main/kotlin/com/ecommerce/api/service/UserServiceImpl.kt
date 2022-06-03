package com.ecommerce.api.service

import com.ecommerce.api.DAO.CartDAO
import com.ecommerce.api.DAO.OrderHistoryDAO
import com.ecommerce.api.DAO.ProductAddedToCartDAO
import com.ecommerce.api.DAO.UserDAO
import com.ecommerce.api.exception.AddException
import com.ecommerce.api.exception.GetAllException
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.exception.UnauthorizedException
import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.models.User
import org.bson.types.ObjectId
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
    var userDao: UserDAO,
    var cartDAO: CartDAO,
    var orderHistoryDAO: OrderHistoryDAO,
    var productAddedToCartDAO: ProductAddedToCartDAO,
    var cartService: CartService,
    var orderHistoryService: OrderHistoryService
) : UserService {

    override fun addUser(user: User): User {
        if (userDao.existsByEmail(user.email)) {
            throw AddException("Email already exists")
        } else {
            user.password = randomAlphanumericString()
            var subject: String = "Welcome to Electroics store"
            var user_Id = ObjectId().toString()
            var body: String =
                "Hi ${user.name},\n Your  account created successfully.\n Your details are as follows: \nName: ${user.name}\nEmail: ${user.email}\nPassword: ${user.password}\nPhone Number: ${user.phoneNo}.\n\n Thanks,\n Shopping Management Team"

            //var addCart : Cart = Cart( user.email,null,"",0,0,0,0.0,"","","","","","")
            //cartDAO.save(addCart)
            //var order: OrderHistory= OrderHistory(user.email,"",null,"",null)
            //orderHistoryDAO.save(order)

            return this.userDao.insert(user)
        }
    }

    override fun getUserById(email: String): User {

        if (userDao.existsByEmail(email)) return this.userDao.findById(email).get()
        else throw GetException("User not found, Please register....")
    }


    override fun changePaymentAgree(id: String, payThrough: String, paymentID: String): Cart {

        var cart : Cart = cartDAO.findById(id).get()
        //var order: OrderHistory = orderHistoryService.getOrderHistoryById(email)

        //var productCart = this.productAddedToCartDAO.findAll()

        if (cart.agree.equals("Pending")) {


            cart.shipmentStatus = "Your order has been placed successfully and will be delivered shortly."
            cart.agree = "Approved"
            cart.paid = "Paid"
            //order.cart?.paid="Paid"
            cart.payThrough = payThrough
            cart.paymentID = paymentID
            cart.products?.clear()
            //productCart.clear()

            //cart.products?.removeAll(productCart)
           // orderHistoryDAO.save(order)

            cartDAO.save(cart)

            return cart
        } else {
            cart.shipmentStatus = "Thank you for using our website services"
            cart.agree = "Declined"
            cart.paid = "Not Paid"

            cartDAO.save(cart)

            return cart
        }


    }

    override fun changeCart(id: String): OrderHistory {
        var cart : Cart = cartDAO.findById(id).get()
        var order: OrderHistory = orderHistoryDAO.findById(id).get()

        if(cart.paid.equals("Paid")){
            order.deliveryStatus="Not Delivered"
            order.products?.clear()
            cart.agree="Nothing"
            cart.paid="Pending"
            cart.payThrough=""
            cart.paymentID=""
            cart.shipmentStatus="Waiting for review"
            cartDAO.save(cart)
            orderHistoryDAO.save(order)
            return order
        }
        else{
            return order
        }
    }

    override fun changeDeliveryStatus(id: String): OrderHistory {
        var cart : Cart = cartDAO.findById(id).get()
        var order: OrderHistory = orderHistoryDAO.findById(id).get()

        if(cart.paid.equals("Paid")){

            order.deliveryStatus="Delivered"
            orderHistoryDAO.save(order)
            return order

        }else{
            order.deliveryStatus="Not Delivered"
            orderHistoryDAO.save(order)
            return order

        }
    }


    override fun getAllUser(): List<User> {
        if (userDao.count() > 0) return this.userDao.findAll()
        else throw GetAllException("No User found, Please register....")
    }

    override fun deleteById(email: String): String {
        if (userDao.existsByEmail(email)) {
            this.userDao.deleteById(email)

            return "Deleted successfully"
        } else {
            throw UnauthorizedException("Please login")
        }
    }


    fun randomAlphanumericString(): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return List(8) { charset.random() }.joinToString("")
    }


}