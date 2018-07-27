package lt.boldadmin.sektor.mobile.android.publisher.test.unit.factory

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.factory.CredentialsFactory
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CredentialsFactoryTest {

    @Mock
    private lateinit var builderMock: GoogleCredential.Builder

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Test
    fun `Creates Google services credentials`() {
        val googleCredentialMock = mock<GoogleCredential>()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())
        doReturn(builderMock).`when`(builderMock).transport = any()
        doReturn(builderMock).`when`(builderMock).jsonFactory = any()
        doReturn(builderMock).`when`(builderMock).serviceAccountId = any()
        doReturn(builderMock).`when`(builderMock).serviceAccountScopes = any()
        doReturn(builderMock).`when`(builderMock).setServiceAccountPrivateKeyFromP12File(any())
        doReturn(googleCredentialMock).`when`(builderMock).build()

        val edits = CredentialsFactory(
            builderMock,
            propertyLoaderMock
        ).createCredentials()

        assertEquals(googleCredentialMock, edits)
        verify(propertyLoaderMock, times(2)).load(any())
        verify(builderMock).transport = any()
        verify(builderMock).jsonFactory = any()
        verify(builderMock).serviceAccountId = any()
        verify(builderMock).serviceAccountScopes = any()
        verify(builderMock).setServiceAccountPrivateKeyFromP12File(any())
        verify(builderMock).build()
    }

}