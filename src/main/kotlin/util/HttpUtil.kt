package util

import io.reactivex.Observable

@Suppress("UNCHECKED_CAST")
class HttpUtil {
    fun <T> getResult(clazz:Class<T>,observable: Observable<T>) : T {
        val c = Class.forName(clazz.name)

        var result = c.getDeclaredConstructor().newInstance() as T
        observable.subscribe(
                {
                    result = it
                },
                {
                    print(it.message)
                })
        return result
    }
}