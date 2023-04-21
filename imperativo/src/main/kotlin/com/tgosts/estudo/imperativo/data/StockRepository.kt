package com.tgosts.estudo.imperativo.data

import com.tgosts.estudo.imperativo.vo.StockRequest
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*

@Service
class StockRepository {

    fun save(stockRequest: StockRequest) : StockRequest {
        stockRequest.id = UUID.randomUUID().toString()
        Files.write(Paths.get("output.txt"),(stockRequest.toString()+"\n").toByteArray(),StandardOpenOption.APPEND,StandardOpenOption.CREATE)
        return stockRequest
    }
}
