package lt.boldadmin.sektor.mobile.android.publisher.test.unit

import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Tracks
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Tracks.Update
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Commit
import com.google.api.services.androidpublisher.model.Apk
import com.google.api.services.androidpublisher.model.AppEdit
import com.google.api.services.androidpublisher.model.Track
import com.google.api.services.androidpublisher.model.TrackRelease
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.ApkLoader
import lt.boldadmin.sektor.mobile.android.publisher.EditsService
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import lt.boldadmin.sektor.mobile.android.publisher.factory.TrackReleaseFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class EditsServiceTest {

    @Mock private lateinit var editsMockSpy: AndroidPublisher.Edits

    @Mock private lateinit var propertyLoaderSpy: PropertyLoader

    @Mock private lateinit var apkLoaderSpy: ApkLoader

    @Mock private lateinit var trackSpy: Track

    @Mock private lateinit var trackFactorySpy: TrackReleaseFactory

    private lateinit var editsService: EditsService

    @Before
    fun setUp() {
        editsService = EditsService(editsMockSpy, propertyLoaderSpy, apkLoaderSpy, trackSpy, trackFactorySpy)
    }

    @Test
    fun `Creates a new Edit`() {
        val insertSpy = mock<AndroidPublisher.Edits.Insert>()
        val editDummy = mock<AppEdit>()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())
        doReturn(insertSpy).`when`(editsMockSpy).insert(any(), eq(null))
        doReturn(editDummy).`when`(insertSpy).execute()

        val newEdit = editsService.createNewEdit()

        assertEquals(editDummy, newEdit)
        verify(propertyLoaderSpy).load(any())
        verify(editsMockSpy).insert(any(), eq(null))
        verify(insertSpy).execute()
    }

    @Test
    fun `Uploads apk to Edit`() {
        val apksSpy = mock<AndroidPublisher.Edits.Apks>()
        val uploadSpy = mock<AndroidPublisher.Edits.Apks.Upload>()
        doReturn(apksSpy).`when`(editsMockSpy).apks()
        doReturn(uploadSpy).`when`(apksSpy).upload(any(), eq(editId), any())
        doReturn(mock<FileContent>()).`when`(apkLoaderSpy).loadApk()
        doReturn(mock<Apk>()).`when`(uploadSpy).execute()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())

        editsService.uploadApk(editId)

        verify(editsMockSpy).apks()
        verify(apksSpy).upload(any(), eq(editId), any())
        verify(apkLoaderSpy).loadApk()
        verify(uploadSpy).execute()
        verify(propertyLoaderSpy).load(any())
    }

    @Test
    fun `Updates Track info`() {
        val tracksSpy = mock<Tracks>()
        val updateSpy = mock<Update>()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())
        doReturn(tracksSpy).`when`(editsMockSpy).tracks()
        doReturn(updateSpy).`when`(tracksSpy).update(any(), eq(editId), any(), any())
        doReturn(mock<Track>()).`when`(updateSpy).execute()
        doReturn(mock<TrackRelease>()).`when`(trackFactorySpy).create()
        doReturn(mock<Track>()).`when`(trackSpy).releases = any()

        editsService.updateTrack(editId)

        verify(propertyLoaderSpy, times(2)).load(any())
        verify(editsMockSpy).tracks()
        verify(tracksSpy).update(any(), eq(editId), any(), any())
        verify(trackFactorySpy).create()
        verify(trackSpy).releases = any()
        verify(updateSpy).execute()
    }

    @Test
    fun `Commits Track changes`() {
        val commitSpy = mock<Commit>()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())
        doReturn(commitSpy).`when`(editsMockSpy).commit(any(), eq(editId))
        doReturn(mock<AppEdit>()).`when`(commitSpy).execute()

        editsService.commit(editId)

        verify(propertyLoaderSpy).load(any())
        verify(editsMockSpy).commit(any(), eq(editId))
        verify(commitSpy).execute()
    }

    companion object {
        const val editId = "editId"
    }

}