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

    fun getUser(username: String): User {
        return HttpUtil().getResult(User::class.java, mService.getUser(username))
    }

}