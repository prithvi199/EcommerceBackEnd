package com.ecommerce.api.service

import com.ecommerce.api.DAO.AdminDAO
import com.ecommerce.api.exception.AddException
import com.ecommerce.api.exception.DeleteException
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.Address
import com.ecommerce.api.models.Admin
import com.ecommerce.api.models.AdminTemp
import org.springframework.stereotype.Service

@Service
public class AdminServiceImpl(
    var adminDAO: AdminDAO
) : AdminService {
    override fun addAdmin(adminTemp: AdminTemp): Admin {
        if (this.adminDAO.existsByEmail(adminTemp.email))
            throw AddException("Already exists...")
        else {

            return this.adminDAO.save(
                Admin(
                    adminTemp.email,
                    adminTemp.password,
                    adminTemp.name,
                    adminTemp.phoneNo,
                    Address(adminTemp.doorNo, adminTemp.place, adminTemp.city, adminTemp.state, adminTemp.pincode)
                )
            )
        }
    }

    override fun viewAdmin(email: String): Admin {
        if (this.adminDAO.existsByEmail(email))
            return this.adminDAO.findById(email).get()
        else
            throw GetException("No admin found in this email id: " + email)
    }

    override fun deleteAdmin(email: String): String {
        if (this.adminDAO.existsByEmail(email)) {
            this.adminDAO.deleteById(email)
            return "Deleted Sucessfully"
        } else
            throw DeleteException("No admin found in this email id: " + email)
    }
}