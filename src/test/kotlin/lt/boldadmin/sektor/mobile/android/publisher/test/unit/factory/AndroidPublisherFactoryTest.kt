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
class AndroidPublisherFactoryTest{

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Mock
    private lateinit var publisherBuilderMock: AndroidPublisherBuilder

    @Test
    fun `Creates AndroidPublisher`() {
        val builderMock = mock<AndroidPublisher.Builder>()
        val publisherMock = mock<AndroidPublisher>()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())
        doReturn(builderMock).`when`(publisherBuilderMock).createBuilder()
        doReturn(builderMock).`when`(builderMock).applicationName = any()
        doReturn(publisherMock).`when`(builderMock).build()

        val publisher = AndroidPublisherFactory(
            propertyLoaderMock,
            publisherBuilderMock
        ).create()

        assertEquals(publisherMock, publisher)
        verify(publisherBuilderMock).createBuilder()
        verify(builderMock).applicationName = any()
        verify(builderMock).build()
    }
}