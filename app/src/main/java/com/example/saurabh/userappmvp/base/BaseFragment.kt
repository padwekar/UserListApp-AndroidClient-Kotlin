package com.example.saurabh.userappmvp.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith

abstract class BaseFragment<T : BasePresenter> : DialogFragment() {

    open var presenter : T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backButtonEnabled()
        presenter = createPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val relationShipWith = this::class.annotations.find { it is InRelationShipWith } as? InRelationShipWith
        relationShipWith?.run {
            return inflater.inflate(relationShipWith.resource,container,false)
        }?: kotlin.run {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    fun backButtonEnabled(enabled : Boolean = true){
        if(activity is BaseActivity){
            (activity as BaseActivity).backButtonEnabled(enabled)
        }
    }

    fun pop(){
        if(activity is BaseActivity){
            (activity as BaseActivity).onBackPressed()
        }
    }

    abstract fun createPresenter() : T
}