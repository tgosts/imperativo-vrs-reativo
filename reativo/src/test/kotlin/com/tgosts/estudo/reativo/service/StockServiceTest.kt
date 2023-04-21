package com.tgosts.estudo.reativo.service

import com.tgosts.estudo.reativo.data.StockRepository
import com.tgosts.estudo.reativo.mock.MarketStock
import com.tgosts.estudo.reativo.vo.StatusReqeust
import com.tgosts.estudo.reativo.vo.StockRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.BDDMockito.*
import reactor.test.StepVerifier

@SpringBootTest(classes = [StockService::class])
class StockServiceTest {

    @Autowired
    lateinit var stockService: StockService

    @MockBean
    lateinit var stockRepository: StockRepository

    @MockBean
    lateinit var marketStock: MarketStock

    @Test
    fun shouldCallOrderApiSuccess(){

        var stockRequestInit = StockRequest(amount =  5 , ticket = "PBK", proposal = 500.0, id = null, value = null, statusRequest = null, maxSelling = null)
        var stockRequestLoad = StockRequest(amount =  5 , ticket = "PBK", proposal = 500.0, id = null, value = 99.9, statusRequest = StatusReqeust.APROVED, maxSelling = 10)
        var stockRequestSave = StockRequest(amount =  5 , ticket = "PBK", proposal = 500.0, id = null, value = 99.9, statusRequest = StatusReqeust.APROVED, maxSelling = 10)

        given(marketStock.loadData(stockRequestInit)).willReturn(stockRequestLoad)
        given(stockRepository.save(stockRequestLoad)).willReturn(stockRequestSave)


        StepVerifier.create(stockService.buy(stockRequestInit)).expectNext(stockRequestSave).verifyComplete()

        then(marketStock).should().loadData(stockRequestInit)
        then(stockRepository).should().save(stockRequestLoad)

    }

}