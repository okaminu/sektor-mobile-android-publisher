package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.services.androidpublisher.AndroidPublisher
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader

class AndroidPublisherFactory(
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val credentialsFactory: CredentialsFactory = CredentialsFactory()
) {

    fun create(): AndroidPublisher =
        AndroidPublisher.Builder(
            createHttpTransport(),
            createJacksonInstance(),
            credentialsFactory.create()
        )
            .setApplicationName(propertyLoader.load("publisher.properties")["APPLICATION_NAME"].toString())
            .build()

}