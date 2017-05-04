package me.donnie.github.ui.profile.overview

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import me.donnie.github.common.utils.RxUtils
import me.donnie.github.common.utils.SchedulerTransformer
import me.donnie.github.data.entity.User
import me.donnie.github.data.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/4/28.
 * @description
 * @version
 */
class OverViewPresenter @Inject constructor(var repository: UserRepository) : OverViewContract.Presenter {

    companion object{
        private val TAG = OverViewPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: OverViewContract.View? = null

    private var mDisposable = CompositeDisposable()

    override fun attachView(view: OverViewContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    override fun loadUser() {
        mDisposable.add(repository.user
                .compose(SchedulerTransformer<User>())
                .subscribe(object : Consumer<User> {
                    override fun accept(user: User) {
                        onUserResponse(user)
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(e: Throwable) {
                        Timber.tag(TAG).e(e.message)
                        view?.onError()
                    }
                }))
    }

    override fun onUserResponse(user: User) {
        view?.loadUserSuccess(user)
    }
}