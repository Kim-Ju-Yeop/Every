package com.example.every.fragment.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.every.R
import com.example.every.base.view.student.BaseStudentFragment

class StudentScheduleFragment : BaseStudentFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_schedule, container, false)
    }
}
