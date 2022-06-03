package com.ecommerce.api.controllers

import com.ecommerce.api.models.Admin
import com.ecommerce.api.models.AdminTemp
import com.ecommerce.api.service.AdminService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
class AdminController(var adminService: AdminService) {

    @PostMapping
    fun addAdmin(@Valid @RequestBody adminTemp: AdminTemp): Admin = this.adminService.addAdmin(adminTemp)

    @GetMapping("/{email}")
    fun getAdmin(@PathVariable email: String): Admin {
        return this.adminService.viewAdmin(email)   }






    @DeleteMapping("/{email}")
    fun deleteAdmin(@PathVariable email: String): String = this.adminService.deleteAdmin(email)
}