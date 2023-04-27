package com.example.rx_java

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demo_app.databinding.ActivitySecondBinding
import com.example.rx_java.RxBus.Companion.behaviorSubject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class SecondActivity :BaseActivity<ActivitySecondBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivitySecondBinding
        get() = ActivitySecondBinding::inflate
    private val compositeDisposable = CompositeDisposable()

    override fun addCallBacks() {
        getCounter()
    }


    @SuppressLint("CheckResult")
    private fun getCounter() {
       behaviorSubject.subscribe { countString ->
            binding.textViewCount.text = countString
        }.add(compositeDisposable)

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
    companion object{
        fun Disposable.add(compositeDisposable: CompositeDisposable){
            compositeDisposable.add(this)
        }
    }
}