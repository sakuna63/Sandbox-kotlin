import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

/**
 * Created by sakuna63 on 2017/02/06.
 */

fun main(args : Array<String>) {
    var retrofit = Retrofit.Builder()
            .baseUrl("https://www.drupal.org")
            .build()
    var api = retrofit.create(DupalApi::class.java)
    var map: Map<String, Any> = mapOf("hoge" to "fuga")
    var response = api.getUser(map).execute()
    println(response)
}

interface DupalApi {
    @FormUrlEncoded
    @POST("/api-d7/user.json")
    fun getUser(@FieldMap map: java.util.Map<String, Any>) : Call<ResponseBody>
}