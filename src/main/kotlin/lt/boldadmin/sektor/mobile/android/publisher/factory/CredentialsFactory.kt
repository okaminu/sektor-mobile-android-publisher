package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import java.io.File

class CredentialsFactory(
    private val builder: GoogleCredential.Builder = GoogleCredential.Builder(),
    private val propertyLoader: PropertyLoader = PropertyLoader()
) {

    fun createCredentials(): GoogleCredential {
        return builder
            .setTransport(createHttpTransport())
            .setJsonFactory(createJacksonInstance())
            .setServiceAccountId(propertyLoader.load("config.properties")["SERVICE_ACCOUNT_EMAIL"].toString())
            .setServiceAccountScopes(setOf(AndroidPublisherScopes.ANDROIDPUBLISHER))
            .setServiceAccountPrivateKeyFromP12File(
                File(propertyLoader.load("publisher.properties")["SRC_RESOURCES_KEY_P12"].toString())
            )
            .build()
    }
}