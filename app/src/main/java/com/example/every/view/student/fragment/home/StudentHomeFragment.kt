package com.example.every.view.student.fragment.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.every.R
import com.example.every.base.BaseFragment
import com.example.every.base.StudentData
import com.example.every.databinding.FragmentStudentHomeBinding
import com.example.every.view.signin.SignInActivity
import com.example.every.view.student.activity.bamboo.BambooCommentActivity
import com.example.every.viewmodel.student.fragment.StudentMainFragmentViewModel

class StudentHomeFragment : BaseFragment() {

    lateinit var binding : FragmentStudentHomeBinding
    lateinit var viewModel : StudentMainFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_home, container, false)
        viewModel = ViewModelProviders.of(this@StudentHomeFragment).get(StudentMainFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@StudentHomeFragment

        checkInit()
        viewModel.getMeals()
        viewModel.getStudentInfo(binding.studentName)
        observerViewModel()
        return binding.root
    }

    private fun checkInit(){
        val checkToken : SharedPreferences = context!!.getSharedPreferences("checkToken", Context.MODE_PRIVATE)
        StudentData.token.value = checkToken.getString("tokenData", null)

        val checkIdentity : SharedPreferences = context!!.getSharedPreferences("checkIdentity", Context.MODE_PRIVATE)
        StudentData.studentIdx.value = checkIdentity.getInt("identityIdx_Student", -1)
    }

    override fun observerViewModel() {
        with(viewModel){
            onBreakfastEvent.observe(this@StudentHomeFragment, Observer {
                val adapter = MealsAdapter(viewModel.breakfastList)
                binding.recyclerView.adapter = adapter

                if(adapter.itemCount == 0) setVisible(binding.questionLayout, binding.recyclerView, 0)
                else setVisible(binding.questionLayout, binding.recyclerView, 1)

                binding.mealsTitle.text = "아침"
                binding.mealsImage.setImageResource(R.drawable.sunrise_breakfast)
                binding.mealsBackground.setBackgroundResource(R.drawable.background_breakfast)
                viewModel.checkCount = 1
            })
            onLunchEvent.observe(this@StudentHomeFragment, Observer {
                val adapter = MealsAdapter(viewModel.lunchList)
                binding.recyclerView.adapter = adapter

                if(adapter.itemCount == 0) setVisible(binding.questionLayout, binding.recyclerView, 0)
                else setVisible(binding.questionLayout, binding.recyclerView, 1)

                binding.mealsTitle.text = "점심"
                binding.mealsImage.setImageResource(R.drawable.sun_lunch)
                binding.mealsBackground.setBackgroundResource(R.drawable.background_lunch)
                viewModel.checkCount = 2
            })
            onDinnerEvent.observe(this@StudentHomeFragment, Observer {
                val adapter = MealsAdapter(viewModel.dinnerList)
                binding.recyclerView.adapter = adapter

                if(adapter.itemCount == 0) setVisible(binding.questionLayout, binding.recyclerView, 0)
                else setVisible(binding.questionLayout, binding.recyclerView, 1)

                binding.mealsTitle.text = "저녁"
                binding.mealsImage.setImageResource(R.drawable.moon_dinner)
                binding.mealsBackground.setBackgroundResource(R.drawable.background_dinner)
                viewModel.checkCount = 3
            })
            onFailEvent.observe(this@StudentHomeFragment, Observer {
                setVisible(binding.questionLayout, binding.recyclerView, 0)
            })
            onTokenEvent.observe(this@StudentHomeFragment, Observer {
                val loginData = context!!.applicationContext.getSharedPreferences("checkLogin", Context.MODE_PRIVATE)
                val loginData_editor = loginData.edit()

                loginData_editor.putBoolean("loginData", false)
                loginData_editor.commit()

                toastMessage(binding.root.context, "토큰이 만료되었습니다. 로그인 화면으로 이동합니다.")
                startActivity(Intent(context!!.applicationContext, SignInActivity::class.java))
                activity!!.finish()
            })
            onSuccessEvent.observe(this@StudentHomeFragment, Observer {
                val intent = Intent(binding.root.context, BambooCommentActivity::class.java)
                intent.putExtra("idx", viewModel.bambooOrderList.get(0).idx)
                intent.putExtra("created_at", "★ 오늘의 인기 게시글")
                intent.putExtra("content", viewModel.bambooOrderList.get(0).content)
                startActivity(intent)
            })
        }
    }
}
