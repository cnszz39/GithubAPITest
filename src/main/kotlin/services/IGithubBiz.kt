package services

import io.reactivex.Observable
import model.SearchResult
import model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IGithubBiz {

    /**
     * 指定内容で検索し、結果を戻す
     *
     * @param searchWord 検索内容
     * @return 検索結果
     */
    @GET("/search/repositories")
    fun searchRepositories(@Query("q") searchWord: String): Observable<SearchResult>

    /**
     * ユーザーネームで該当ユーザーの詳細情報を取得する
     *
     * @param username ユーザーネーム
     * @return ユーザーインフォ
     */
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Observable<User>

    /**
     * 端末で認証したのユーザーの情報を取得する
     *
     * @return ユーザーインフォ
     */
    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>


    fun updateAuthenticatedUser():Observable<User>
}