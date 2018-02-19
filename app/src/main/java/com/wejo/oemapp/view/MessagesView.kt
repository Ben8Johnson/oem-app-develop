package com.wejo.oemapp.view

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.example.benjohnson.messageviewproject.MessageScript
import com.example.benjohnson.messageviewproject.Question
import com.google.android.flexbox.FlexboxLayout
import com.wejo.oemapp.ImageTakenListener
import com.wejo.oemapp.R
import com.wejo.oemapp.activities.FnolActivity
import com.wejo.oemapp.databinding.ViewMessagesBinding
import com.wejo.oemapp.utils.Constants


/**
 * Created by BenJohnson on 21/11/2017.
 */
class MessagesView : RelativeLayout {

    var messageScript: MessageScript? = null
    private lateinit var binding: ViewMessagesBinding
    var currentQuestion = MutableLiveData<Int>()

    constructor(context: Context?) : super(context) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize()
    }

    /**
     * ReadScript and initialize view
     */
    private fun initialize() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_messages, this, true)
        messageScript?.let {
            getNextQuestion()
        }
    }

    fun addQuestion(messageScript: MessageScript) {
        this.messageScript = messageScript
        currentQuestion.value = 0
        getNextQuestion()
    }

    private fun getNextQuestion() {
        when (messageScript?.questions?.get(currentQuestion.value!!)?.responseType) {
            Question.RESPONSE_TYPE.TEXT -> populateViewForTextResponse()
            Question.RESPONSE_TYPE.OPTIONAL -> populateViewForOptionalResponse()
            Question.RESPONSE_TYPE.LOCATION -> populateViewForLocationResponse()
            Question.RESPONSE_TYPE.IMAGE -> populateViewForLocationResponse()
        }
    }

    /**
     * If the questions response type is requesting images then we will
     * give the user to take or uplaod an image
     */
    private fun populateViewForLocationResponse() {
        //Display question to the user
        addQuestion(messageScript?.questions?.get(currentQuestion.value!!)?.question!!)

        // Create Response view
        val responseView = LayoutInflater.from(context).inflate(R.layout.view_image_response, binding.llMessages, false)
        binding.viewResponse.addView(responseView)

        (context as FnolActivity).setImageRecivedListener(object : ImageTakenListener {
            override fun imageTaken(bitmap: Bitmap) {
                userHasTakenPhoto(bitmap)
            }

        })
        responseView.findViewById<TextView>(R.id.tv_take_photo).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(context.packageManager) != null)
                    (context as Activity).startActivityForResult(takePictureIntent, Constants.TAKE_PHOTO_REQUEST_CODE)
            }

        })
        //If user chooses to upload from camera role
        responseView.findViewById<TextView>(R.id.tv_upload_photo).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                (context as Activity).startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), Constants.PHOTO_FROM_GALLERY_REQUEST_CODE)
            }
        })
        //If user chooses to upload from camera role
        responseView.findViewById<TextView>(R.id.tv_sent_images).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                currentQuestion.value = currentQuestion.value!! + 1
            }
        })
    }


    /**
     * If the questions response type is multiple choice then we will populate the users response input
     * with each choice so that they can select the correct response
     */
    private fun populateViewForOptionalResponse() {
        addQuestion(messageScript?.questions?.get(currentQuestion.value!!)?.question!!)

        //Create response view
        val responseView = LayoutInflater.from(context).inflate(R.layout.view_text_response, binding.llMessages, false)
        binding.viewResponse.addView(responseView)

        val optionalChoiceView = LayoutInflater.from(context).inflate(R.layout.view_optional_response, this, false)
        binding.viewResponse.addView(optionalChoiceView)

        for (option in messageScript!!.questions[currentQuestion.value!!].options!!) {
            val optionView = LayoutInflater.from(context).inflate(R.layout.view_message_option, binding.llMessages, false)
            optionView.findViewById<TextView>(R.id.tv_option).text = option
            optionalChoiceView.findViewById<FlexboxLayout>(R.id.fbl_multiple_choice).addView(optionView)
            optionView.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    //Save answer
                    messageScript?.questions?.get(currentQuestion.value!!)?.response = option
                    currentQuestion.value = currentQuestion.value!! + 1
                    addAnswer(option)
                    //Clear for next question
                    binding.viewResponse.removeAllViews()
                    //Next question
//                    getNextQuestion()
                }
            })

        }

    }

    /**
     * If the questions expected response type is text then we will create a view with an edit text
     * field so that the user can enter their response
     */
    private fun populateViewForTextResponse() {
        addQuestion(messageScript?.questions?.get(currentQuestion.value!!)?.question!!)


        val responseView = LayoutInflater.from(context).inflate(R.layout.view_text_response, binding.llMessages, false)
        binding.viewResponse.addView(responseView)

        responseView.findViewById<ImageButton>(R.id.btn_send).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                //Save answer
                messageScript?.questions?.get(currentQuestion.value!!)?.response = responseView.findViewById<EditText>(R.id.tv_message_out).text.toString()
                currentQuestion.value = currentQuestion.value!! + 1
                addAnswer(responseView.findViewById<EditText>(R.id.tv_message_out).text.toString())
                //Clear for next question
                binding.viewResponse.removeAllViews()
                //Next question
//                getNextQuestion()
            }
        })

    }

    fun addQuestion(question: String) {
        //Display question to the user
        val optionalQuestion = LayoutInflater.from(context).inflate(R.layout.view_message_bubbles, binding.llMessages, false)
        optionalQuestion.findViewById<TextView>(R.id.tv_message_in).text = question
        optionalQuestion.findViewById<TextView>(R.id.tv_message_in).visibility = View.VISIBLE
        binding.llMessages.addView(optionalQuestion)
        val anim = AnimationUtils.loadAnimation(context, R.anim.design_bottom_sheet_slide_in)
        anim.duration = 500
        optionalQuestion.startAnimation(anim)

    }

    fun addAnswer(answer: String) {
        //Create new bubble for the sent message
        val sentMessage = LayoutInflater.from(context).inflate(R.layout.view_message_bubbles, binding.llMessages, false)
        //populate bubble with the text the user just entered
        sentMessage.findViewById<TextView>(R.id.tv_message_out).text = answer
        sentMessage.findViewById<TextView>(R.id.tv_message_out).visibility = View.VISIBLE
        binding.llMessages.addView(sentMessage)
        val anim = AnimationUtils.loadAnimation(context, R.anim.design_bottom_sheet_slide_in)
        anim.duration = 500
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                getNextQuestion()
            }

        })
        sentMessage.startAnimation(anim)

    }

    fun userHasTakenPhoto(bitmap: Bitmap) {
        //Display question to the user
        val optionalQuestion = LayoutInflater.from(context).inflate(R.layout.view_message_bubbles, binding.llMessages, false)
        //TODO: ADD IN PROPPER SCALLING
        val bmpimg = Bitmap.createScaledBitmap(bitmap, 300, 300, true)

        optionalQuestion.findViewById<ImageView>(R.id.tv_image_out).setImageBitmap(bmpimg)
        optionalQuestion.findViewById<ImageView>(R.id.tv_image_out).visibility = View.VISIBLE
        binding.llMessages.addView(optionalQuestion)
    }


    fun generateFullReport(): MessageScript? {
        return null
    }
}