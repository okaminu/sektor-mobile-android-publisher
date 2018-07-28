package lt.boldadmin.sektor.mobile.android.publisher

import com.google.api.client.http.FileContent
import java.io.File

class ApkLoader(private val propertyLoader: PropertyLoader = PropertyLoader()) {

    fun load(path: String) = FileContent(
        propertyLoader.load("publisher.properties")["MIME_TYPE_APK"].toString(),
        File(path)
    )

}