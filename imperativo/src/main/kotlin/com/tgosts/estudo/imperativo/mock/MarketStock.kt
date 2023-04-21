package com.tgosts.estudo.imperativo.mock

import com.tgosts.estudo.imperativo.vo.StockRequest
import org.springframework.stereotype.Component

@Component
class MarketStock {
    companion object {
        val stocks = listOf<Stock>(
            Stock("DIS", 300, 88.5),
            Stock("SLG", 480, 90.7),
            Stock("AMZ", 350, 100.98),
            Stock("JNJ", 200, 45.5),
            Stock("KBL", 50, 56.8),
            Stock("ZTM", 400, 188.3),
            Stock("KOK", 18, 78.8),
            Stock("FUR", 546, 108.1),
            Stock("NIK", 651, 28.22),
            Stock("BZN", 333, 48.5),
            Stock("AAZ", 412, 25.56),
            Stock("APL", 195, 76.45),
            Stock("DLL", 500, 83.7),
            Stock("LGT", 412, 37.52),
            Stock("BMC", 222, 58.18),
            Stock("PBK", 313, 82.84),
        )

    }

    fun loadData( stockRequest: StockRequest) : StockRequest {
        var marketStock = stocks.filter { s -> s.ticket == stockRequest.ticket}.first()
        stockRequest.value = marketStock.value
        stockRequest.maxSelling = marketStock.marcketUnit
        return stockRequest
    }
}

data class Stock(

    val ticket: String,

    val marcketUnit: Int,

    val value: Double

)