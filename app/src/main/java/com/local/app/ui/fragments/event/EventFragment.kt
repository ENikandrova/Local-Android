package com.local.app.ui.fragments.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.local.app.BuildConfig
import com.local.app.data.event.Event
import com.local.app.databinding.FragmentEventFullBinding
import com.local.app.presentation.viewmodel.event.EventViewModel
import com.local.app.ui.BaseFragment
import com.local.app.ui.fragments.event.state.EventState
import com.local.app.ui.photo.CommonRVEventElements

class EventFragment : BaseFragment() {

    lateinit var binding: FragmentEventFullBinding
    lateinit var viewModel: EventViewModel

    companion object {
        fun create(eventId: Long): EventFragment {
            val fragment = EventFragment()
            val args = Bundle()
            args.putLong(EVENT_ID, eventId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentEventFullBinding.inflate(inflater)

        viewModel = ViewModelProviders
            .of(this)
            .get(EventViewModel::class.java)

        getDagger().feedComponent?.inject(this)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.eventState.observe(this, Observer {
            when (it) {
                is EventState.Success -> showEvent(it.event)
                is EventState.Error -> it.throwable.printStackTrace()
            }
        })
        refreshData()

        BuildConfig.DEBUG
    }

    private fun showEvent(event: Event) {
        binding.event = event
        binding.executePendingBindings()
        CommonRVEventElements.buildTagsView(binding.llTags, event.tagsDefault)
        CommonRVEventElements.showImages(binding.rvImages, event.pictures)
    }

    private fun refreshData() {
        Log.d("Event id ", "" + arguments?.getLong(EVENT_ID, -1))
        arguments
            ?.getLong(EVENT_ID, -1)
            ?.let { viewModel.getEventById(it) }
    }
}

public const val EVENT_ID = "EVENT_ID"