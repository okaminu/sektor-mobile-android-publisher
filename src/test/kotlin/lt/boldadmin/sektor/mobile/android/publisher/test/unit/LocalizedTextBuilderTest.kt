package lt.boldadmin.sektor.mobile.android.publisher.test.unit

import com.google.api.services.androidpublisher.model.LocalizedText
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.LocalizedTextBuilder
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class LocalizedTextBuilderTest {

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Mock
    private lateinit var localizedTextMock: LocalizedText

    @Test
    fun `Sets listing update text`() {
        val textMock = mock<LocalizedText>()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())
        doReturn(localizedTextMock).`when`(localizedTextMock).language = any()
        doReturn(textMock).`when`(localizedTextMock).text = any()

        val text = LocalizedTextBuilder(propertyLoaderMock, localizedTextMock).build()

        assertEquals(textMock, text)
        verify(propertyLoaderMock, times(2)).load(any())
        verify(localizedTextMock).language = any()
        verify(localizedTextMock).text = any()
    }

}