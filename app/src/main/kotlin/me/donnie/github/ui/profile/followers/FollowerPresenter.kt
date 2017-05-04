package me.donnie.github.ui.profile.followers

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.github.common.utils.RxUtils
import me.donnie.github.common.utils.SchedulerTransformer
import me.donnie.github.data.entity.User
import me.donnie.github.data.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
class FollowerPresenter @Inject constructor(var repository: UserRepository) : FollowerContract.Presenter {

    companion object{
        private val TAG = FollowerPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: FollowerContract.View? = null

    private var mDisposable = CompositeDisposable()

    private var currentPage = 1

    override fun attachView(view: FollowerContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    fun refresh() {
        this.currentPage = 1
        val username = prefser.get("username", String::class.java, "")
        loadUserFollower(username, currentPage)
    }

    fun loadmore() {
        this.currentPage++
        val username = prefser.get("username", String::class.java, "")
        loadUserFollower(username, currentPage)
    }

    override fun loadUserFollower(username: String, page: Int) {
        mDisposable.add(repository.getFollowers(username, page)
                .compose(SchedulerTransformer<List<User>>())
                .subscribe({ users -> onFollowerResponse(users)},
                        { e -> Timber.tag(TAG).e(e.message)
                                view?.onError() }))
    }

    override fun onFollowerResponse(users: List<User>) {
        view?.loadUserFollowerSuccess(users, currentPage)
    }
}