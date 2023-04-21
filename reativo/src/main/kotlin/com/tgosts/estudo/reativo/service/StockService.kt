package com.tgosts.estudo.reativo.service

import com.tgosts.estudo.reativo.data.StockRepository
import com.tgosts.estudo.reativo.mock.MarketStock
import com.tgosts.estudo.reativo.mock.Stock
import com.tgosts.estudo.reativo.vo.StatusReqeust
import com.tgosts.estudo.reativo.vo.StockRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class StockService (

    @Autowired
    val stockRepository: StockRepository,

    @Autowired
    val marketStock: MarketStock

        ) {
    fun buy(stockRequest: StockRequest): Mono<StockRequest> {

        return Mono.just(stockRequest)
            .filter { it -> validateStockRequest(it) }
            .doOnError{ System.out.println("INVALID!") }
            .doOnSuccess{ System.out.println("VALID!") }
            .map { stockRequest -> marketStock.loadData(stockRequest) }
            .map { it -> validateCharge(it) }
            .map { it -> stockRepository.save(it) }

    }
    fun validateStockRequest( stockRequest: StockRequest) : Boolean{
        val untilOneRequest = stockRequest.amount > 0
        val isAValidTicket = MarketStock.stocks.filter { s -> s.ticket == stockRequest.ticket }.isNotEmpty()
        return untilOneRequest && isAValidTicket
    }

    fun validateCharge( stockRequest: StockRequest) : StockRequest {
        val charge = stockRequest.value?.times(stockRequest.amount)
        if( charge!! > stockRequest.proposal ) {
            stockRequest.statusRequest = StatusReqeust.REPORVED
        }else{
            stockRequest.statusRequest = StatusReqeust.APROVED
        }
        return stockRequest
    }


}