package com.local.app.ui.fragments.event.create

import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.local.app.BindableFragment
import com.local.app.R
import com.local.app.presentation.viewmodel.event.create.CreateEventViewModel
import com.local.app.ui.BaseFragment

abstract class BaseCreateEventFragment<T : ViewBinding> : BindableFragment<T>() {

    val model: CreateEventViewModel by activityViewModels()

    open fun getPrevFragment() {}

    open fun getNextFragment(): BaseFragment? {
        return null
    }

    override fun initUI() {
        super.initUI()
        binding.root
            .findViewById<Button>(R.id.btn_close)
            .setOnClickListener { requireActivity().finish() }

        binding.root
            .findViewById<Button>(R.id.btn_back)
            ?.setOnClickListener { requireActivity().onBackPressed() }
    }

    fun goNext() {
        getNextFragment()?.let {
            showFragment(it, true)
        }
    }
}