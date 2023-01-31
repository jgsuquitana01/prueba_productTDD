package com.example.facturasapi.controller

import com.example.facturasapi.model.Product
import com.example.facturasapi.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository


    fun list ():List<Product>{
        return productRepository.findAll()
    }

    /*fun save (product: Product):Product{
      try {
        product.stock?.takeIf { it > 0}
          ?: throw Exception("stock no valido")
          //client.fullname?.takeIf { it.stock > 0 }
         //              ?: throw Exception("fullname no debe ser vacio")
        return productRepository.save(product)
    }
      catch(ex:Exception){
        throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
      }

    }*/
    fun save(product: Product): Product {
        try{
            product.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Error de placa")

            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(product: Product):Product{
        try {
            productRepository.findById(product.id)
                ?: throw Exception("El id ${product.id} en producto no existe")
            return productRepository.save(product)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(product: Product):Product{
        try{
            val response = productRepository.findById(product.id)
                ?:throw Exception("El ${product.id} en producto no existe")
            return productRepository.save(product)
            response.apply{
                stock = product.stock
                price = product.price
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }
    fun validateLicensePlate(plate:String):Boolean{
        val code = Regex("^[A-Z] {3} -\\d[0-9] {3}\$")
        return true
    }

}


