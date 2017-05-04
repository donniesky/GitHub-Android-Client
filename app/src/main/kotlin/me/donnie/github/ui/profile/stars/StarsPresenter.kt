package me.donnie.github.ui.profile.stars

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.github.common.utils.RxUtils
import me.donnie.github.common.utils.SchedulerTransformer
import me.donnie.github.data.entity.Repo
import me.donnie.github.data.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
class StarsPresenter @Inject constructor(var repository: UserRepository) : StarsContract.Presenter {

    companion object{
        private val TAG = StarsPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: StarsContract.View? = null

    private var mDisposable = CompositeDisposable()

    private var currentPage = 1

    override fun attachView(view: StarsContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    fun refresh() {
        this.currentPage = 1
        val username = prefser.get("username", String::class.java, "")
        loadstars(username, currentPage)
    }

    fun loadmore() {
        this.currentPage++
        val username = prefser.get("username", String::class.java, "")
        loadstars(username, currentPage)
    }

    override fun loadstars(username: String, page: Int) {
        mDisposable.add(repository.getStars(username, page)
                .compose(SchedulerTransformer<List<Repo>>())
                .subscribe({ repos -> onStarsResponse(repos)},
                        { e -> Timber.tag(TAG).e(e.message)
                                view?.onError()}))
    }

    override fun onStarsResponse(repos: List<Repo>) {
        view?.loadStarSuccess(repos, currentPage)
    }

}