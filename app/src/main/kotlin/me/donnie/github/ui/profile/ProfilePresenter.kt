package me.donnie.github.ui.profile

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import me.donnie.github.common.utils.RxUtils
import me.donnie.github.common.utils.SchedulerTransformer
import me.donnie.github.data.entity.User
import me.donnie.github.data.repository.UserRepository
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/4/27.
 * @description
 * @version
 */
class ProfilePresenter @Inject constructor(var mRepository: UserRepository) :
ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    @Inject
    lateinit var prefser: Prefser

    private var mDisposable = CompositeDisposable()

    override fun attachView(view: ProfileContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    override fun loadUser() {
        mDisposable.add(mRepository.user
                .compose(SchedulerTransformer<User>())
                .subscribe(object : Consumer<User> {
                    override fun accept(user: User) {
                        onUserResponse(user)
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(e: Throwable) {
                        view?.onError()
                    }
                }))
    }

    override fun onUserResponse(user: User) {
        view?.loadUserSuccess(user)
    }
}