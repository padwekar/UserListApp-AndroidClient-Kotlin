package com.example.saurabh.userappmvp.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.ui.BasePresenter

abstract class BaseFragment<T : BasePresenter> : DialogFragment() {

    open var presenter : T? = null

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

}