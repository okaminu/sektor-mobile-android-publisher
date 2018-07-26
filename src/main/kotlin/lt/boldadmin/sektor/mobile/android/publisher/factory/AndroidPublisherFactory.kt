package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.services.androidpublisher.AndroidPublisher
import lt.boldadmin.sektor.mobile.android.publisher.AndroidPublisherBuilder
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader

class AndroidPublisherFactory(
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val builder: AndroidPublisherBuilder = AndroidPublisherBuilder()
) {


    fun create(): AndroidPublisher =
        builder
            .createBuilder()
            .setApplicationName(propertyLoader.load("publisher.properties")["APPLICATION_NAME"].toString())
            .build()

}