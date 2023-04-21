package com.tgosts.estudo.reativo.controller

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator
import com.tgosts.estudo.reativo.data.StockRepository
import com.tgosts.estudo.reativo.service.StockService
import com.tgosts.estudo.reativo.vo.StockRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/stock")
class StockController (

    @Autowired
    val stockService: StockService

    ){
    @PostMapping("/buy")
    fun compra(@RequestBody stockRequest: StockRequest) : Mono<StockRequest>{

        val event = Mono.just(stockRequest)
        return event.flatMap{it -> stockService.buy(it)}
            .doOnSuccess{System.out.println(stockRequest)}

    }

}