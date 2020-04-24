package com.local.app.ui.feed

import android.os.Build
import android.view.Gravity.CENTER_VERTICAL
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.local.app.R
import com.local.app.data.Event
import com.local.app.databinding.ItemRvEventBinding
import com.local.app.ui.Utils
import com.local.app.ui.photo.CommonRVEventElements

abstract class FeedRVAdapter(private var events: List<Event>) :
    RecyclerView.Adapter<FeedRVAdapter.VH>() {

    sealed class Clicks {
        class Like(var eventId: Long) : Clicks()
        class Dislike(var eventId: Long) : Clicks()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(events[position])
        val rvImages = holder.binding.rvImages

        CommonRVEventElements.buildTagsView(holder.binding.llTags, events[position].tagsDefault)
        CommonRVEventElements.showImages(rvImages, events[position].pictures)

        holder.binding.ivDislike.setOnClickListener {
            onClicks(Clicks.Dislike(events[position].id))
        }
        holder.binding.ivLike.setOnClickListener { onClicks(Clicks.Like(events[position].id)) }

    }

    abstract fun onClicks(click: Clicks)

    inner class VH(var binding: ItemRvEventBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            binding.event = event
            binding.executePendingBindings()
        }
    }

}