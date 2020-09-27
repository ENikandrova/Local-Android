package com.local.app.ui.fragments.event.create

import android.view.LayoutInflater
import com.local.app.databinding.FragmentCreateEventStep3Binding
import com.local.app.ui.BaseFragment

class CreateEventStep3Fragment : BaseCreateEventFragment<FragmentCreateEventStep3Binding>() {

    override fun setBinding(inflater: LayoutInflater) {
        binding = FragmentCreateEventStep3Binding.inflate(inflater)
    }

    override fun initUI() {
        super.initUI()

    }

    override fun getNextFragment(): BaseFragment {
        return CreateEventStep4Fragment()
    }

}