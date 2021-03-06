package com.local.app.presentation.viewmodel.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.local.app.LocalApp
import com.local.app.domain.feed.LoadFeedInteractor
import com.local.app.ui.fragments.feed.state.FeedState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    var feedState: MutableLiveData<FeedState> = MutableLiveData()

    @Inject
    lateinit var getEventsInteractor: LoadFeedInteractor

    init {
        getApplication<LocalApp>().daggerManager.plusFeedComponent()
        getApplication<LocalApp>().daggerManager.feedComponent?.inject(this)
    }

    fun loadFeed() {
        disposable.add(getEventsInteractor
                           .execute()
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe({ feedState.value = FeedState.Success(it) },
                                      { feedState.value = FeedState.Error(it) }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}