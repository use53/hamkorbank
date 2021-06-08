package uz.mapbank.mapbankapi.model

class Payment(
    val paymentName:String,
    val id:Long=0
)

class PaymentItem(){
    fun paymentList():MutableList<Payment> =

         mutableListOf<Payment>().apply {
             add(Payment("Mobile communications"))
             add(Payment("Public services"))
             add(Payment("Internet"))
             add(Payment("ID-card,passport and registration"))
             add(Payment("Internet services"))
             add(Payment("Home telephone"))
             add(Payment("IP Telephony"))
             add(Payment("TV"))
             add(Payment("Taxi"))
             add(Payment("Electronic money"))
             add(Payment("Money transfers"))
             add(Payment("Fines SAORS"))
             add(Payment("Insurance"))
             add(Payment("Tax payment"))
             add(Payment("Payment to budget"))
             add(Payment("Bank transfers"))

    }
    fun loanOnline():MutableList<Payment> =

        mutableListOf<Payment>().apply {
            add(Payment("Repayment of loans Hamkorbank"))
            add(Payment("Repayment of loans any banks"))


        }

    fun newsItem():MutableList<News> =

        mutableListOf<News>().apply {
            add(News("Dear customers","19.10.2020","Now you can transfer money to the account"))
        }


}