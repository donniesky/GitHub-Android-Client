package me.donnie.github.ui.profile.repos

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
class ReposPresenter @Inject constructor(var repository: UserRepository) : ReposContract.Presenter {

    companion object{
        private val TAG = ReposPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: ReposContract.View? = null

    private var mDisposable = CompositeDisposable()

    private var currentPage = 1

    override fun attachView(view: ReposContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        RxUtils.dispose(mDisposable)
    }

    fun refresh() {
        this.currentPage = 1
        val username = prefser.get("username", String::class.java, "")
        loadRepos(username, currentPage)
    }

    fun loadmore() {
        this.currentPage++
        val username = prefser.get("username", String::class.java, "")
        loadRepos(username, currentPage)
    }

    override fun loadRepos(username: String, page: Int) {
        mDisposable.add(repository.getRepos(username, page)
                .compose(SchedulerTransformer<List<Repo>>())
                .subscribe({ repos -> onReposResponse(repos)},
                        { e -> Timber.tag(TAG).e(e.message)
                        view?.onError()}))
    }

    override fun onReposResponse(repos: List<Repo>) {
        view?.loadRepoSuccess(repos, currentPage)
    }
}