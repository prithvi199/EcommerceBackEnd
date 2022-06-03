package com.ecommerce.api.service

import com.ecommerce.api.DAO.AdminDAO
import com.ecommerce.api.DAO.CartDAO
import com.ecommerce.api.DAO.OrderHistoryDAO
import com.ecommerce.api.DAO.UserDAO
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.Admin
import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.models.User
import com.ecommerce.api.util.JwtUtils
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletResponse

@Service
class LoginServiceImpl(
    var userDAO: UserDAO,
    var adminDAO: AdminDAO,
    var userService: UserService,
    var adminService: AdminService,
    var cartDAO: CartDAO,
    var orderHistoryDAO: OrderHistoryDAO,

    var utils: JwtUtils
) : LoginService {

    override fun login(email: String, password: String, response: HttpServletResponse): Any {

        if (adminDAO.existsByEmailAndPassword(email,password)) {
            var admin: Admin = this.adminService.viewAdmin(email)
            admin.isLoggedIn = true
            adminDAO.save(admin)

            val jwt = utils.generateJwt(admin.email,admin.type!!, response)
            var loginList: Any = mutableListOf(admin.type, admin.isLoggedIn,jwt)
            return loginList

        } else if (userDAO.existsByEmailAndPassword(email, password)) {
            //var user_Id = ObjectId().toString()
            var user: User = this.userService.getUserById(email)
            user.isLoggedIn = true

            userDAO.save(user)

          // var addCart : Cart = Cart(user_Id,null, "","",0,0,0,0.0,"","","","","","")
            //cartDAO.save(addCart)
//
//            var addCart: Cart = Cart(user_Id, null, "", 0, 0, 0, 0.0, "")
//            cartDAO.save(addCart)
       // var order: OrderHistory= OrderHistory(user_Id,"","",null,"",null)
            //orderHistoryDAO.save(order)

            val jwt = utils.generateJwt(user.email, user.type!!, response)
            var loginList: Any = mutableListOf(user.type, user.isLoggedIn, jwt)
            return loginList

        }
        else throw GetException("Email and password Not Found")
    }

    override fun logout(email: String): String {
        if (adminDAO.existsByEmail(email)) {
            var admin: Admin = this.adminService.viewAdmin(email)
            admin.isLoggedIn = false
            val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val now = LocalDateTime.now()
            admin.lastLogin = dtf.format(now)
            adminDAO.save(admin)
            return "Logout successful"
        } else if (userDAO.existsByEmail(email)) {
            var user: User = this.userService.getUserById(email)
            user.isLoggedIn = false
            val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val now = LocalDateTime.now()
            user.lastLogin = dtf.format(now)
            userDAO.save(user)
            return "Logout successful"

        }
        else throw GetException("Inavlid email")
    }

}