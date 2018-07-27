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
    private lateinit var builderSpy: GoogleCredential.Builder

    @Mock
    private lateinit var propertyLoaderStub: PropertyLoader

    @Test
    fun `Creates Google services credentials`() {
        val googleCredentialDummy = mock<GoogleCredential>()
        doReturn(mock<Properties>()).`when`(propertyLoaderStub).load(any())
        doReturn(builderSpy).`when`(builderSpy).transport = any()
        doReturn(builderSpy).`when`(builderSpy).jsonFactory = any()
        doReturn(builderSpy).`when`(builderSpy).serviceAccountId = any()
        doReturn(builderSpy).`when`(builderSpy).serviceAccountScopes = any()
        doReturn(builderSpy).`when`(builderSpy).setServiceAccountPrivateKeyFromP12File(any())
        doReturn(googleCredentialDummy).`when`(builderSpy).build()

        val edits = CredentialsFactory(builderSpy, propertyLoaderStub).createCredentials()

        assertEquals(googleCredentialDummy, edits)
        verify(propertyLoaderStub, times(2)).load(any())
        verify(builderSpy).transport = any()
        verify(builderSpy).jsonFactory = any()
        verify(builderSpy).serviceAccountId = any()
        verify(builderSpy).serviceAccountScopes = any()
        verify(builderSpy).setServiceAccountPrivateKeyFromP12File(any())
        verify(builderSpy).build()
    }

}