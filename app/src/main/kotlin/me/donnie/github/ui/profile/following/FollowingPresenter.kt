package me.donnie.github.ui.profile.following

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
 * @created_at 2017/5/4.
 * @description
 * @version
 */
class FollowingPresenter @Inject constructor(var repository: UserRepository) : FollowingContract.Presenter {

    companion object{
        private val TAG = FollowingPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: FollowingContract.View? = null

    private var mDisposable = CompositeDisposable()

    private var currentPage = 1

    override fun attachView(view: FollowingContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    fun refresh() {
        this.currentPage = 1
        val username = prefser.get("username", String::class.java, "")
        loadFollowing(username, currentPage)
    }

    fun loadmore() {
        this.currentPage++
        val username = prefser.get("username", String::class.java, "")
        loadFollowing(username, currentPage)
    }

    override fun loadFollowing(username: String, page: Int) {
        mDisposable.add(repository.getFollowing(username, page)
                .compose(SchedulerTransformer<List<User>>())
                .subscribe( { users -> onFollowingResponse(users) } ,
                            { e -> Timber.tag(TAG).e(e.message)
                                    view?.onError() }))
    }

    override fun onFollowingResponse(users: List<User>) {
        view?.loadFollowingSuccess(users, currentPage)
    }

}