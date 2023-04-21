package com.tgosts.estudo.imperativo.service

import com.tgosts.estudo.imperativo.data.StockRepository
import com.tgosts.estudo.imperativo.mock.MarketStock
import com.tgosts.estudo.imperativo.vo.StatusReqeust
import com.tgosts.estudo.imperativo.vo.StockRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StockService (

    @Autowired
    val stockRepository: StockRepository,

    @Autowired
    val marketStock: MarketStock

) {
    fun buy(stockRequest: StockRequest): StockRequest {

        if (validateStockRequest(stockRequest)) {
            System.out.println("VALID!")
            marketStock.loadData(stockRequest)
            validateCharge(stockRequest)
            stockRepository.save(stockRequest)
        } else {
            System.out.println("INVALID!")
        }
        return stockRequest

    }

    fun validateStockRequest(stockRequest: StockRequest): Boolean {
        val untilOneRequest = stockRequest.amount > 0
        val isAValidTicket = MarketStock.stocks.filter { s -> s.ticket == stockRequest.ticket }.isNotEmpty()
        return untilOneRequest && isAValidTicket
    }

    fun validateCharge(stockRequest: StockRequest): StockRequest {
        val charge = stockRequest.value?.times(stockRequest.amount)
        if (charge!! > stockRequest.proposal) {
            stockRequest.statusRequest = StatusReqeust.REPORVED
        } else {
            stockRequest.statusRequest = StatusReqeust.APROVED
        }
        return stockRequest
    }
}