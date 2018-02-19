package com.example.benjohnson.messageviewproject

/**
 * Created by BenJohnson on 21/11/2017.
 */
class Question(var question: String, var responseType: RESPONSE_TYPE, var response: String?, var options: MutableList<String>?) {

    enum class RESPONSE_TYPE {
        TEXT, OPTIONAL, LOCATION, IMAGE
    }
}