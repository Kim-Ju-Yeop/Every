package com.example.every.view.student.fragment.schedule

import android.content.Context
import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import com.example.every.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(val context : Context, val dates : Collection<CalendarDay>) : DayViewDecorator{

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        Log.e("test", day.toString())
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(DotSpan(15F, Color.parseColor("#2D008A")))
    }
}