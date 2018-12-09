package util

import io.reactivex.Observable

@Suppress("UNCHECKED_CAST")
class HttpUtil {
    fun <T> getResult(clazz:Class<T>,observable: Observable<T>) : T {
        val c = Class.forName(clazz.name)

        var result:T =c.newInstance() as T
        observable.subscribe(
                {
                    result = it
                },
                {
                    it.printStackTrace()
                })
        return result
    }

    fun <T> getResult(clazz:Class<T>,observable: Observable<MutableList<T>>) : MutableList<T> {
        val c = Class.forName(clazz.name)

        var result:MutableList<T> = mutableListOf()
        observable.subscribe(
                {
                    result = it
                },
                {
                    it.printStackTrace()
                })
        return result
    }

}