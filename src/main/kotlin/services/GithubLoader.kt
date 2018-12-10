package services

import model.Repository
import model.User
import util.HttpUtil

class GithubLoader {
    private val mService = RetrofitServiceManager().getInstance().create(IGithubBiz::class.java)

//    fun searchRepositories(searchWord: String): MutableList<Repository> {
////        val repositories = mutableListOf<Repository>()
////        mService.searchRepositories(searchWord).subscribe(
////                {
////                    if (it.totalCount > 0) {
////                        repositories.addAll(it.items)
////                    }
////                }, {
////            it.printStackTrace()
////        })
//
//        return HttpUtil().getResult(Repository::class.java,mService.searchRepositories(searchWord))
//    }

    /**
     * ユーザーネームで該当ユーザーの詳細情報を取得する
     *
     * @param username ユーザーネーム
     * @return ユーザーインフォ
     */
    fun getUser(username: String): User {
        return HttpUtil().getResult(User::class.java, mService.getUser(username))
    }

    /**
     * 端末で認証したのユーザーの情報を取得する
     *
     * @return ユーザーインフォ
     */
    fun getAuthenticatedUser() : User {
        return HttpUtil().getResult(User::class.java, mService.getAuthenticatedUser())
    }

    fun updateAuthenticatedUser():User {

        return HttpUtil().getResult(User::class.java, mService.getAuthenticatedUser())
    }
}