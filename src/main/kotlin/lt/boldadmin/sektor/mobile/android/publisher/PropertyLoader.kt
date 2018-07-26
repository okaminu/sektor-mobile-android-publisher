package lt.boldadmin.sektor.mobile.android.publisher

import java.util.*

class PropertyLoader {
    fun load(path: String) = Properties().apply {
        PropertyLoader::class.java.classLoader.getResourceAsStream(path).use { it ->
            load(it)
        }
    }
}