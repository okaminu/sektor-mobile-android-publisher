package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.services.androidpublisher.model.LocalizedText
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader

class LocalizedTextFactory(
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val localizedText: LocalizedText = LocalizedText()
) {

    fun create(): LocalizedText =
        localizedText
            .setLanguage(propertyLoader.load("listing.properties")["LANGUAGE"].toString())
            .setText(propertyLoader.load("listing.properties")["UPDATE_TEXT"].toString())

}