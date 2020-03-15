package com.example.every.view.student.fragment.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseFragment
import com.example.every.base.StudentData
import com.example.every.databinding.FragmentStudentHomeBinding
import com.example.every.view.signin.SignInActivity
import com.example.every.view.student.activity.bamboo.BambooCommentActivity
import com.example.every.view.student.fragment.home.adapter.MealsAdapter
import com.example.every.viewmodel.student.fragment.StudentHomeFragmentViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

class StudentHomeFragment : BaseFragment() {

    lateinit var binding : FragmentStudentHomeBinding
    lateinit var viewModel : StudentHomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_home, container, false)
        viewModel = ViewModelProviders.of(this@StudentHomeFragment).get(StudentHomeFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentHomeFragment

        init()
        todaySchedule()
        viewModel.getMeals()
        viewModel.getStudentInfo(binding.studentName)
        return binding.root
    }

    private fun init(){
        val checkToken : SharedPreferences = context!!.getSharedPreferences("checkToken", Context.MODE_PRIVATE)
        StudentData.token.value = checkToken.getString("tokenData", null)

        val checkIdentity : SharedPreferences = context!!.getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
        StudentData.studentIdx.value = checkIdentity.getInt("identityIdx_Student", -1)
    }

    private fun todaySchedule(){
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse("${CalendarDay.today().year}-${CalendarDay.today().month+1}-${CalendarDay.today().day}")
        val todayDate = SimpleDateFormat("yyyy-MM-dd").format(date)
        val todayDate2 = SimpleDateFormat("yyyy년 MM월 dd일").format(date)

        binding.todayTextView.text = todayDate2
        viewModel.getSchedule(todayDate)
    }

    override fun observerViewModel() {
        with(viewModel){
            onBreakfastEvent.observe(this@StudentHomeFragment, Observer {
                val adapter = MealsAdapter(viewModel.breakfastList)
                binding.recyclerView.adapter = adapter

                if(adapter.itemCount == 0) setVisible(binding.questionLayout, binding.recyclerView, 0)
                else setVisible(binding.questionLayout, binding.recyclerView, 1)

                binding.mealsTitle.text = "아침"
                binding.mealsImage.setImageResource(R.drawable.icon_breakfast)
                binding.mealsBackground.setBackgroundResource(R.drawable.image_breafkast)
                viewModel.checkCount = 1
            })
            onLunchEvent.observe(this@StudentHomeFragment, Observer {
                val adapter = MealsAdapter(viewModel.lunchList)
                binding.recyclerView.adapter = adapter

                if(adapter.itemCount == 0) setVisible(binding.questionLayout, binding.recyclerView, 0)
                else setVisible(binding.questionLayout, binding.recyclerView, 1)

                binding.mealsTitle.text = "점심"
                binding.mealsImage.setImageResource(R.drawable.icon_lunch)
                binding.mealsBackground.setBackgroundResource(R.drawable.image_lunch)
                viewModel.checkCount = 2
            })
            onDinnerEvent.observe(this@StudentHomeFragment, Observer {
                val adapter = MealsAdapter(viewModel.dinnerList)
                binding.recyclerView.adapter = adapter

                if(adapter.itemCount == 0) setVisible(binding.questionLayout, binding.recyclerView, 0)
                else setVisible(binding.questionLayout, binding.recyclerView, 1)

                binding.mealsTitle.text = "저녁"
                binding.mealsImage.setImageResource(R.drawable.icon_dinner)
                binding.mealsBackground.setBackgroundResource(R.drawable.image_dinner)
                viewModel.checkCount = 3
            })
            onMealsFailureEvent.observe(this@StudentHomeFragment, Observer {
                setVisible(binding.questionLayout, binding.recyclerView, 0)

                binding.mealsTitle.text = "점심"
                binding.mealsImage.setImageResource(R.drawable.icon_lunch)
                binding.mealsBackground.setBackgroundResource(R.drawable.image_lunch)
                viewModel.checkCount = 2
            })
            onTokenEvent.observe(this@StudentHomeFragment, Observer {
                val loginData = context!!.applicationContext.getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                val loginData_editor = loginData.edit()

                loginData_editor.putBoolean("loginData", false)
                loginData_editor.commit()

                toastMessage(binding.root.context, "자동 로그인 시간이 만료되었습니다.")
                startActivity(Intent(context!!.applicationContext, SignInActivity::class.java))
                activity!!.finish()
            })
            onLoadingEvent.observe(this@StudentHomeFragment, Observer {
                toastMessage(binding.root.context, "대나무숲을 조회 중입니다.")
            })
            onBambooDataEvent.observe(this@StudentHomeFragment, Observer {
                val intent = Intent(binding.root.context, BambooCommentActivity::class.java)
                intent.putExtra("idx", viewModel.bambooOrderList.get(0).idx)
                intent.putExtra("created_at", "★ 오늘의 인기 게시글")
                intent.putExtra("content", viewModel.bambooOrderList.get(0).content)
                startActivity(intent)
            })
            onScheduleDataEvent.observe(this@StudentHomeFragment, Observer {
                if(viewModel.counter == 0) binding.counterTextView.text = "오늘은 일정이 존재하지 않습니다!"
                else binding.counterTextView.text = "오늘은 일정이 ${viewModel.counter}개 존재합니다!"
            })
            onHomeActivityEvent.observe(this@StudentHomeFragment, Observer {
                toastMessage(binding.root.context, "현재 개발 진행중인 기능입니다.")
            })
        }
    }
}
