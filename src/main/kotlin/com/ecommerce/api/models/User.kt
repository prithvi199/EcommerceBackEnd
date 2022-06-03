package com.ecommerce.api.models
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.executable.ValidateOnExecution


@Document(collection= "shopping_cart")
@ValidateOnExecution
data class User (

    @Id

    @field:Email(message = "Email should be valid")
    var email:String,
    @JsonIgnore
    var type:String? = "User",
    var password:String?,
    @field:NotBlank(message = "Name can't be blank, it's mandatory")
    var name:String,
    @field: Pattern(regexp="^\$|[0-9]{10}", message = "Should be valid phone number")
    var phoneNo:String,
    @field: Valid
    var address:Address?,

    @Transient
    @JsonIgnore
    var isLoggedIn:Boolean? = false,
    //var order:MutableList<OrderHistory> = mutableListOf<OrderHistory>(),
    var orderHistory:MutableList<OrderHistory>? = mutableListOf<OrderHistory>(),
    var lastLogin: String? =""






){constructor(email:String,
name:String,
phoneNo:String,
address:Address):this(email,"User",null,name,phoneNo,address,null,null,"")
}