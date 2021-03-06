package com.local.app.di.feed

import com.local.app.di.scopes.PerFeed
import com.local.app.presentation.viewmodel.PhotoViewerViewModel
import com.local.app.presentation.viewmodel.event.EventViewModel
import com.local.app.presentation.viewmodel.feed.EventsFeedViewModel
import com.local.app.ui.fragments.event.EventDetailsFragment
import com.local.app.ui.photo.PhotoViewerFragment
import dagger.Subcomponent

@PerFeed
@Subcomponent(modules = [FeedModule::class])
interface FeedComponent {
    fun inject(photoViewerFragment: PhotoViewerFragment)
    fun inject(photoViewerFragment: PhotoViewerViewModel)
    fun inject(eventFragment: EventDetailsFragment)
    fun inject(eventsFeedViewModel: EventsFeedViewModel)
    fun inject(eventViewModel: EventViewModel)

}