package com.example.rx_java

import android.database.Observable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    abstract val bindingInflater: (LayoutInflater) -> VB
    private var _binding: ViewBinding? = null
    protected val binding
        get() = _binding as VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        addCallBacks()
    }

    protected abstract fun addCallBacks()
    companion object {
        const val TAG = "123123123"
    }
}