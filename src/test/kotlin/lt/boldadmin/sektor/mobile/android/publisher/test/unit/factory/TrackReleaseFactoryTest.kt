package lt.boldadmin.sektor.mobile.android.publisher.test.unit.factory

import com.google.api.services.androidpublisher.model.TrackRelease
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.LocalizedTextBuilder
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import lt.boldadmin.sektor.mobile.android.publisher.factory.TrackReleaseFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TrackReleaseFactoryTest{

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Mock
    private lateinit var trackRelease: TrackRelease

    @Mock
    private lateinit var textBuilderMock: LocalizedTextBuilder

    @Test
    fun `Sets listing update details`() {
        val trackMock = mock<TrackRelease>()
        val propertiesMock = mock<Properties>()
        doReturn(propertiesMock).`when`(propertyLoaderMock).load(any())
        doReturn(versionCode).`when`(propertiesMock)[any()]
        doReturn(trackRelease).`when`(trackRelease).name = any()
        doReturn(trackRelease).`when`(trackRelease).versionCodes = any()
        doReturn(trackRelease).`when`(trackRelease).status = any()
        doReturn(trackMock).`when`(trackRelease).releaseNotes = any()

        val track = TrackReleaseFactory(
            trackRelease,
            propertyLoaderMock,
            textBuilderMock
        ).create()

        assertEquals(trackMock, track)
        verify(propertyLoaderMock, times(2)).load(any())
    }

    companion object {
        const val versionCode = "5"
    }
}