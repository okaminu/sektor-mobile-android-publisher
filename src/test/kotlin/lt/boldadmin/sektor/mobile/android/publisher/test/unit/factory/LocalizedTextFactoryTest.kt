package lt.boldadmin.sektor.mobile.android.publisher.test.unit.factory

import com.google.api.services.androidpublisher.model.LocalizedText
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.factory.LocalizedTextFactory
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class LocalizedTextFactoryTest {

    @Mock
    private lateinit var propertyLoaderSpy: PropertyLoader

    @Mock
    private lateinit var localizedTextSpy: LocalizedText

    @Test
    fun `Sets listing text model`() {
        val textDummy = mock<LocalizedText>()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())
        doReturn(localizedTextSpy).`when`(localizedTextSpy).language = any()
        doReturn(textDummy).`when`(localizedTextSpy).text = any()

        val text = LocalizedTextFactory(propertyLoaderSpy, localizedTextSpy).create()

        assertEquals(textDummy, text)
        verify(propertyLoaderSpy, times(2)).load(any())
        verify(localizedTextSpy).language = any()
        verify(localizedTextSpy).text = any()
    }

}