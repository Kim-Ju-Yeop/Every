package com.project.every.view.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.project.every.view.MainActivity
import com.project.every.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME : Long = 800
    private val REQUEST_CODE_UPDATE = 100

    lateinit var appUpdateManager : AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSplash()
        check()
    }

    private fun setSplash(){
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()}, SPLASH_TIME)
    }

    private fun check() {
        appUpdateManager = AppUpdateManagerFactory.create(this)

        appUpdateManager?.let {
            it.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    // or AppUpdateType.FLEXIBLE
                    appUpdateManager?.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE, // or AppUpdateType.FLEXIBLE
                        this,
                        REQUEST_CODE_UPDATE
                    )

                    val listener = InstallStateUpdatedListener {
                        // Handle install state
                        if (it.installStatus() == InstallStatus.DOWNLOADED) {
                            popupSnackbarForCompleteUpdate()
                        }
                    }
                    appUpdateManager?.registerListener(listener)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_UPDATE) {
            if (resultCode != Activity.RESULT_OK) {
                Toast.makeText(applicationContext, "업데이트가 취소 되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun popupSnackbarForCompleteUpdate() {

        val snackbar = Snackbar.make(findViewById(R.id.layout), "업데이트 버전 다운로드 완료", 5000)
            .setAction("설치/재시작") {
                appUpdateManager?.completeUpdate()
            }
        snackbar.show()
    }
}
