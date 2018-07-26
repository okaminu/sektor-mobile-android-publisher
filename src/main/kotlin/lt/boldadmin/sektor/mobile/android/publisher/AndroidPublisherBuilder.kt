package lt.boldadmin.sektor.mobile.android.publisher

import com.google.api.services.androidpublisher.AndroidPublisher
import lt.boldadmin.sektor.mobile.android.publisher.factory.CredentialsFactory
import lt.boldadmin.sektor.mobile.android.publisher.factory.createHttpTransport
import lt.boldadmin.sektor.mobile.android.publisher.factory.createJacksonInstance

class AndroidPublisherBuilder(private val credentialsFactory: CredentialsFactory = CredentialsFactory()) {

    fun createBuilder() =
        AndroidPublisher.Builder(
            createHttpTransport(),
            createJacksonInstance(), credentialsFactory.createCredentials()
        )

}