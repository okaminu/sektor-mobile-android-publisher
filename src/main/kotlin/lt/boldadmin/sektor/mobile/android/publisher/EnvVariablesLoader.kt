package lt.boldadmin.sektor.mobile.android.publisher

class EnvVariablesLoader {

    fun get(variable: String): String = System.getenv(variable)

}