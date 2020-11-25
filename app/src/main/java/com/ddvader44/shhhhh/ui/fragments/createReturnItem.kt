package com.ddvader44.shhhhh.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ddvader44.shhhhh.R
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.ui.viewmodels.BookViewModel
import com.ddvader44.shhhhh.utils.Calculations
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.fragment_create_return_item.*
import java.text.SimpleDateFormat
import java.util.*

class createReturnItem : Fragment(R.layout.fragment_create_return_item),
TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private var title = ""
    private var description = ""
    private var timeStamp = ""
    private var currentTime=""

    private lateinit var bookViewModel: BookViewModel

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        btn_confirm.setOnClickListener {
            addBookToDb()
        }
        pickDateandTime()
    }

    private fun addBookToDb() {
       title = et_habitTitle.text.toString()
        description = et_habitDescription.text.toString()
        timeStamp = "$cleanDate $cleanTime"
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        currentTime = sdf.format(Date())
        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty())) {
            val book = Book(0,title,description,currentTime,timeStamp)

            bookViewModel.addBook(book)
            Alerter.create(activity)
                .setTitle("Success")
                .setText("Book Return Scheduled successfully at $timeStamp !")
                .setDuration(1000)
                .setIcon(R.drawable.ic_book_alert)
                .setIconColorFilter(0) // removes white filter
                .setEnterAnimation(R.anim.alerter_slide_in_from_left)
                .setExitAnimation(R.anim.alerter_slide_out_to_right)
                .setBackgroundColorInt(Color.YELLOW)
                .show()
            findNavController().navigate(R.id.action_createReturnItem_to_bookList)

        } else{
            Toast.makeText(context,"Please fill all the fields!",Toast.LENGTH_SHORT).show()
        }

    }

    private fun pickDateandTime() {
        btn_pickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(),this,year,month,day).show()
        }

        btn_pickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context,this,hour,minute,true).show()
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minuteX: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay,minuteX)
        tv_timeSelected.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations.cleanDate(dayX, monthX, yearX)
        tv_dateSelected.text = "Date: $cleanDate"
    }

    //get the current time
    private fun getTimeCalendar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    //get the current date
    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

}