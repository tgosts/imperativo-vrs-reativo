package com.tgosts.estudo.imperativo.vo

data class StockRequest(

    var id: String?,

    val ticket: String,

    val amount: Int,

    var value: Double?,

    var statusRequest: StatusReqeust?,

    val proposal: Double,

    var maxSelling: Int?
)

enum class StatusReqeust {
    APROVED, REPORVED
}
