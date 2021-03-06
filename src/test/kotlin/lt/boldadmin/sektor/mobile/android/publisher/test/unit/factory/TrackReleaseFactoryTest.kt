package lt.boldadmin.sektor.mobile.android.publisher.test.unit.factory

import com.google.api.services.androidpublisher.model.TrackRelease
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.EnvVariablesLoader
import lt.boldadmin.sektor.mobile.android.publisher.factory.LocalizedTextFactory
import lt.boldadmin.sektor.mobile.android.publisher.factory.TrackReleaseFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrackReleaseFactoryTest {

    @Mock
    private lateinit var envLoader: EnvVariablesLoader

    @Mock
    private lateinit var trackReleaseSpy: TrackRelease

    @Mock
    private lateinit var textFactorySpy: LocalizedTextFactory

    @Test
    fun `Sets store listing details`() {
        val trackDummy = mock<TrackRelease>()
        doReturn(trackReleaseSpy).`when`(trackReleaseSpy).name = any()
        doReturn(trackReleaseSpy).`when`(trackReleaseSpy).versionCodes = any()
        doReturn(trackReleaseSpy).`when`(trackReleaseSpy).status = any()
        doReturn(trackDummy).`when`(trackReleaseSpy).releaseNotes = any()
        doReturn(versionCode).`when`(envLoader).get(any())

        val track = TrackReleaseFactory(trackReleaseSpy, textFactorySpy, envLoader).create()

        assertEquals(trackDummy, track)
        verify(envLoader, times(2)).get(any())
        verify(trackReleaseSpy).name = any()
        verify(trackReleaseSpy).versionCodes = any()
        verify(trackReleaseSpy).status = any()
        verify(trackReleaseSpy).releaseNotes = any()
        verify(textFactorySpy).create()
    }

    companion object {
        const val versionCode = "5"
    }

}