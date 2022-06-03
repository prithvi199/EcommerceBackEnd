package com.ecommerce.api.service

import com.ecommerce.api.DAO.CartDAO
import com.ecommerce.api.DAO.OrderHistoryDAO
import com.ecommerce.api.DAO.ProductDAO
import com.ecommerce.api.exception.AddException
import com.ecommerce.api.exception.DeleteException
import com.ecommerce.api.exception.GetAllException
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.models.Product
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class ProductServiceImpl(var productDAO: ProductDAO,  var cartDAO: CartDAO,var orderDAO: OrderHistoryDAO) : ProductService {
    override fun addProduct(file: MultipartFile, name: String, price: Double, quantity:Int,description:String): Product {

        var name: String = name
        var product_Id = ObjectId().toString()

        var product = Product(
            product_Id, name, quantity, price,description,
            Base64.getEncoder().encodeToString(file.bytes)
        )
//            var addCart: Cart = Cart(product_Id, null, "", 0, 0, 0, 0.0, "","","","","","")
//            cartDAO.save(addCart)
//        var addOrder: OrderHistory = OrderHistory(product_Id, "",null,"")
//        orderDAO.save(addOrder)
           return this.productDAO.save(product)

    }

    override fun viewProductByName(name: String): List<Product> {
        if (productDAO.existsProductByName(name))
            return this.productDAO.findProductByName(name)
        else
            throw GetException("No product found..... Please initiate to add")
    }






    override fun viewProductById(productId: String): Product {
        if (productDAO.existsByProdId(productId)) return this.productDAO.findById(productId).get()
        else throw GetException("Products does not exists...")
    }

    override fun viewAllProducts(): MutableList<Product> {
        if (productDAO.count() > 0) return this.productDAO.findAll()
        else throw GetAllException("No Product available...")
    }

    override fun deleteProduct(productId: String): String {
        if (productDAO.existsByProdId(productId)) {
            this.productDAO.deleteById(productId)
            return "Deleted Successfully"
        } else throw DeleteException("Product does not exists...")

    }


}
