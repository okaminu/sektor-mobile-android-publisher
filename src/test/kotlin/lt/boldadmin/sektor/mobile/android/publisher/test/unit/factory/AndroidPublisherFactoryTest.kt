package lt.boldadmin.sektor.mobile.android.publisher.test.unit.factory

import com.google.api.services.androidpublisher.AndroidPublisher
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import lt.boldadmin.sektor.mobile.android.publisher.*
import lt.boldadmin.sektor.mobile.android.publisher.factory.AndroidPublisherFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AndroidPublisherFactoryTest {

    @Mock
    private lateinit var propertyLoaderSpy: PropertyLoader

    @Mock
    private lateinit var publisherBuilderSpy: AndroidPublisherBuilder

    @Test
    fun `Allows access to Google Play accounts`() {
        val builderSpy = mock<AndroidPublisher.Builder>()
        val publisherDummy = mock<AndroidPublisher>()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())
        doReturn(builderSpy).`when`(publisherBuilderSpy).create()
        doReturn(builderSpy).`when`(builderSpy).applicationName = any()
        doReturn(publisherDummy).`when`(builderSpy).build()

        val publisher = AndroidPublisherFactory(propertyLoaderSpy, publisherBuilderSpy).create()

        assertEquals(publisherDummy, publisher)
        verify(propertyLoaderSpy).load(any())
        verify(publisherBuilderSpy).create()
        verify(builderSpy).applicationName = any()
        verify(builderSpy).build()
    }

}