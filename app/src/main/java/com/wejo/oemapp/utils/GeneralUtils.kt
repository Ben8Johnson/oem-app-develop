package com.wejo.oemapp.utils

import android.content.Context
import android.location.Address
import android.location.Location
import com.example.benjohnson.messageviewproject.MessageScript
import com.example.benjohnson.messageviewproject.Question
import com.wejo.oemapp.R


class GeneralUtils {
    companion object {

        fun formatAddress(address: Address): String {
            var addressString = address.getAddressLine(0)
            for (line in 1..address.maxAddressLineIndex) {
                addressString += "," + address.getAddressLine(line)
            }
            return addressString
        }

        fun formatLocation(context: Context, location: Location): String {
            return context.getString(R.string.latitude) +
                    location.latitude + ", " +
                    context.getString(R.string.longitude) +
                    location.longitude
        }

        fun getQuestionsForAccident(): MessageScript {

            var question1 = Question("Is this correct?", Question.RESPONSE_TYPE.OPTIONAL, null, arrayListOf("Yes thats right", "No somewhere else"))
            var question2 = Question("Could you give us a brief description of what happned", Question.RESPONSE_TYPE.TEXT, null, null)
            var question3 = Question("Could you send us some images of the car", Question.RESPONSE_TYPE.IMAGE, null, null)
            var questionList: MutableList<Question> = arrayListOf(question1, question2, question3)

            return MessageScript(questionList)
        }




    }
}