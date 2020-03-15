package com.example.every.view.student.fragment.schedule.decorator

import android.content.Context
import android.graphics.Color
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(val context : Context, val dates : Collection<CalendarDay>) : DayViewDecorator{

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(DotSpan(8F, Color.parseColor("#2D008A")))
    }
}