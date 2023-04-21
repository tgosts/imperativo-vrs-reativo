package com.tgosts.estudo.imperativo.controller

import com.tgosts.estudo.imperativo.data.StockRepository
import com.tgosts.estudo.imperativo.service.StockService
import com.tgosts.estudo.imperativo.vo.StockRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stock")
class StockController (

    @Autowired
    val stockService: StockService

    ){
    @PostMapping("/buy")
    fun compra(@RequestBody stockRequest: StockRequest) : StockRequest{

        stockService.buy(stockRequest)
        System.out.println(stockRequest.toString())
        return stockRequest

    }

}