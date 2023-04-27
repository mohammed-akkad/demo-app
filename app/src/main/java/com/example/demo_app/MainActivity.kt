package com.example.rx_java

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import com.example.demo_app.databinding.ActivityMainBinding
import com.example.rx_java.RxBus.Companion.behaviorSubject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    private val clickSubject = BehaviorSubject.create<Unit>()
    var compositeDisposable = CompositeDisposable()
    var count = 0

    override fun addCallBacks() {
        countNumber()
        setData()
    }

    private fun countNumber() {
        binding.buttonSubmit.setOnClickListener {
            count++
        }
    }

    private fun setData() {
        binding.buttonChange.setOnClickListener {
            behaviorSubject.onNext("$count")
            clickSubject.onNext(Unit)
        }
        changeActivity()
    }

    @SuppressLint("CheckResult")
    private fun changeActivity() {
        clickSubject.throttleFirst(2,TimeUnit.SECONDS).subscribe {
            Log.i(TAG, "changeActivity: 123")
            startActivity(Intent(this,SecondActivity::class.java))
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}