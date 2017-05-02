/**
 * Created by sakuna63 on 2017/03/15.
 */

fun main(args: Array<String>) {
    var name: String? = null
    var name2: String? = null
    var name5: String? = null
    val (name3, name4, name6) = guard(name, name2, name5, { name, name2, name5 -> name.length > 0 }) {
        print("all variables are null OR name.length <= 0")
        return
    }
    print(name3.length)
    print(name4.length)
    print(name6.length)
}

inline fun <T> T?.guard(block: () -> Nothing): T {
    if (this == null) {
        block.invoke()
    }
    return this
}

inline fun <T1, T2, T3> guard(t1: T1?, t2: T2?, t3: T3?, prediction: (T1, T2, T3) -> Boolean, block: () -> Nothing): Triple<T1, T2, T3> {
    val triple = Triple(t1.guard(block), t2.guard(block), t3.guard(block))
    val (_t1, _t2, _t3) = triple
    if (!prediction.invoke(_t1, _t2, _t3)) {
        block.invoke()
    }
    return triple
}
