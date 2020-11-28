package com.santhosh.assignment.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.santhosh.assignment.R
import java.time.LocalDate
import java.time.Period
import java.util.*

fun <T> MutableLiveData<T>.isNotEmpty(): Boolean {
    return this.value.toString().isNotEmpty()
}

fun View.hideKeyboard() {
    this.let { v ->
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

object AgeBindingAdapter {
    @BindingAdapter("age")
    @JvmStatic
    fun calculateAge(view: TextView, calendar: Calendar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val period = Period.between(
                LocalDate.of(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ), LocalDate.now()
            )
            view.text = view.resources.getString(
                R.string.detail_dob,
                period.years.toString(),
                (period.months - 1).toString(),
                period.days.toString()
            )
        }
    }

}