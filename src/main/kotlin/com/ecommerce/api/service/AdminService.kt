package com.ecommerce.api.service

import com.ecommerce.api.models.Admin
import com.ecommerce.api.models.AdminTemp

interface AdminService {
    fun addAdmin(adminTemp: AdminTemp): Admin
    fun viewAdmin(email: String): Admin
    fun deleteAdmin(email: String): String


}