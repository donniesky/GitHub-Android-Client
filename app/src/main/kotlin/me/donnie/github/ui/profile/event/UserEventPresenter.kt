package me.donnie.github.ui.profile.event

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.github.common.utils.RxUtils
import me.donnie.github.common.utils.SchedulerTransformer
import me.donnie.github.data.entity.Event
import me.donnie.github.data.repository.UserRepository
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/2.
 * @description
 * @version
 */
class UserEventPresenter @Inject constructor(var repository: UserRepository) :
UserEventContract.Presenter {

    companion object{
        private val TAG = UserEventPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: UserEventContract.View? = null

    private var mDisposable = CompositeDisposable()

    private var currentPage = 1

    override fun attachView(view: UserEventContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    fun refresh() {
        this.currentPage = 1
        val username = prefser.get("username", String::class.java, "")
        loadUserEvents(username, currentPage)
    }

    fun loadmore() {
        this.currentPage++
        val username = prefser.get("username", String::class.java, "")
        loadUserEvents(username, currentPage)
    }

    override fun loadUserEvents(username: String, page: Int) {
        mDisposable.add(repository.getEvents(username, page)
                .compose(SchedulerTransformer<List<Event>>())
                .subscribe({ events -> onUserEventsResponse(events) },
                        { e -> view?.onError() }))
    }

    override fun onUserEventsResponse(events: List<Event>) {
        view?.loadUserEventSuccess(events, currentPage)
    }
}