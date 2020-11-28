package com.ddvader44.shhhhh.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ddvader44.shhhhh.R
import com.ddvader44.shhhhh.data.models.Book
import com.ddvader44.shhhhh.ui.viewmodels.BookViewModel
import com.ddvader44.shhhhh.utils.Calculations
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.fragment_create_return_item.*
import kotlinx.android.synthetic.main.fragment_update_return_item.*
import java.text.SimpleDateFormat
import java.util.*

class updateReturnItem : Fragment(R.layout.fragment_update_return_item),
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
    private val args by navArgs<updateReturnItemArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        et_bookTitle_update.setText(args.selectedBook.book_title)
        et_bookDescription_update.setText(args.selectedBook.book_description)
        btn_confirm_update.setOnClickListener {
            updateBook()
        }
        pickDateandTime()

        setHasOptionsMenu(true)
    }
    private fun updateBook() {
        title = et_bookTitle_update.text.toString()
        description = et_bookDescription_update.text.toString()
        timeStamp = "$cleanDate $cleanTime"
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        currentTime = sdf.format(Date())
        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty())) {
            val book = Book(0,title,description,currentTime,timeStamp)
            bookViewModel.updateBook(book)
            Alerter.create(activity)
                .setTitle("Success")
                .setText("Book Return updated successfully at $timeStamp !")
                .setDuration(2000)
                .setIcon(R.drawable.ic_success)
                .setEnterAnimation(R.anim.alerter_slide_in_from_left)
                .setExitAnimation(R.anim.alerter_slide_out_to_right)
                .setBackgroundColorInt(Color.BLACK)
                .show()
            findNavController().navigate(R.id.action_updateReturnItem_to_bookList)
        }
        else{
            Toast.makeText(context,"Please fill all the fields!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun pickDateandTime() {
        btn_pickDate_update.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(),this,year,month,day).show()
        }

        btn_pickTime_update.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context,this,hour,minute,true).show()
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minuteX: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay,minuteX)
        tv_timeSelected_update.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations.cleanDate(dayX, monthX, yearX)
        tv_dateSelected_update.text = "Date: $cleanDate"
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_delete -> deleteBook(args.selectedBook)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteBook(book : Book){
        bookViewModel.deleteBook(book)
        Alerter.create(activity)
            .setTitle("Success")
            .setText("Book deleted !")
            .setDuration(1000)
            .setIcon(R.drawable.ic_success)
            .setEnterAnimation(R.anim.alerter_slide_in_from_left)
            .setExitAnimation(R.anim.alerter_slide_out_to_right)
            .setBackgroundColorInt(Color.BLACK)
            .show()
        findNavController().navigate(R.id.action_updateReturnItem_to_bookList)
    }

}