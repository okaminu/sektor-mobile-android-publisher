package lt.boldadmin.sektor.mobile.android.publisher

import com.google.api.services.androidpublisher.model.LocalizedText

class LocalizedTextBuilder(
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val localizedText: LocalizedText = LocalizedText()
) {

    fun build(): LocalizedText =
        localizedText
            .setLanguage(propertyLoader.load("listing.properties")["LANGUAGE"].toString())
            .setText(propertyLoader.load("listing.properties")["UPDATE_TEXT"].toString())


}