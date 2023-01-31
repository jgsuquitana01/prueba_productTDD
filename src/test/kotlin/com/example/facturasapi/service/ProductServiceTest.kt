package com.example.facturasapi.service

import com.example.facturasapi.controller.ProductService
import com.example.facturasapi.model.Product
import com.example.facturasapi.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ProductServiceTest {

  @InjectMocks
  lateinit var productService: ProductService

  @Mock
  lateinit var  productRepository: ProductRepository

  var productMock = Product().apply{
    id=1
    description="ABH-365"
    brand="Chevrolet"
    stock=2
  }

  @Test
  fun saveProductCorrect(){
    Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
    val response = productService.save(productMock)
    Assertions.assertEquals(response?.id, productMock.id)
  }

/*  @Test
  fun saveProductWhenStockIsZero(){

    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { stock=0}
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }*/


  @Test
  fun saveProductWhenLicensePlateIsBlank(){
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { description=""}
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

  @Test
  fun saveProductWhenLicensePlateIsSvenNumber(){
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { description="ABH-365"}
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }
  @Test
  fun saveProductFirstLetterOfLicensePlate() {
    Assertions.assertThrows(Exception::class.java) {
      val letra = Regex("^a")
      productMock.apply { description = "ABH-365" }
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)

    }

  }
  @Test
  fun saveProductWhenBrandIsBlank(){
    Assertions.assertThrows(Exception::class.java) {
      productMock.apply { brand=""}
      Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
      productService.save(productMock)
    }

  }

}


