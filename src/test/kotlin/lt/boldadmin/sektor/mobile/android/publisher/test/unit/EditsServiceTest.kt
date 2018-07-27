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

    @Mock
    private lateinit var editsMock: AndroidPublisher.Edits

    @Mock
    private lateinit var propertyLoaderMock: PropertyLoader

    @Mock
    private lateinit var apkLoaderMock: ApkLoader

    @Mock
    private lateinit var trackMock: Track

    @Mock
    private lateinit var trackFactoryMock: TrackReleaseFactory

    private lateinit var editsService: EditsService


    @Before
    fun setUp() {
        editsService = EditsService(editsMock, propertyLoaderMock, apkLoaderMock, trackMock, trackFactoryMock)
    }

    @Test
    fun `Creates a new edit`() {
        val insertMock = mock<AndroidPublisher.Edits.Insert>()
        val editMock = mock<AppEdit>()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())
        doReturn(insertMock).`when`(editsMock).insert(any(), eq(null))
        doReturn(editMock).`when`(insertMock).execute()

        val newEdit = editsService.createNewEdit()

        assertEquals(editMock, newEdit)
        verify(propertyLoaderMock).load(any())
        verify(editsMock).insert(any(), eq(null))
        verify(insertMock).execute()
    }

    @Test
    fun `Uploads apk to Edit`() {
        val apksMock = mock<AndroidPublisher.Edits.Apks>()
        val uploadMock = mock<AndroidPublisher.Edits.Apks.Upload>()
        doReturn(apksMock).`when`(editsMock).apks()
        doReturn(uploadMock).`when`(apksMock).upload(any(), eq(editId), any())
        doReturn(mock<FileContent>()).`when`(apkLoaderMock).loadApk()
        doReturn(mock<Apk>()).`when`(uploadMock).execute()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())

        editsService.uploadApk(editId)

        verify(editsMock).apks()
        verify(apksMock).upload(any(), eq(editId), any())
        verify(apkLoaderMock).loadApk()
        verify(uploadMock).execute()
        verify(propertyLoaderMock).load(any())
    }

    @Test
    fun `Updates Track info`() {
        val tracksMock = mock<Tracks>()
        val updateMock = mock<Update>()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())
        doReturn(tracksMock).`when`(editsMock).tracks()
        doReturn(updateMock).`when`(tracksMock).update(any(), eq(editId), any(), any())
        doReturn(mock<Track>()).`when`(updateMock).execute()
        doReturn(mock<TrackRelease>()).`when`(trackFactoryMock).create()
        doReturn(mock<Track>()).`when`(trackMock).releases = any()

        editsService.updateTrack(editId)

        verify(propertyLoaderMock, times(2)).load(any())
        verify(editsMock).tracks()
        verify(tracksMock).update(any(), eq(editId), any(), any())
        verify(trackFactoryMock).create()
        verify(trackMock).releases = any()
        verify(updateMock).execute()
    }

    @Test
    fun `Commits Track changes`() {
        val commitMock = mock<Commit>()
        doReturn(mock<Properties>()).`when`(propertyLoaderMock).load(any())
        doReturn(commitMock).`when`(editsMock).commit(any(), eq(editId))
        doReturn(mock<AppEdit>()).`when`(commitMock).execute()

        editsService.commit(editId)

        verify(propertyLoaderMock).load(any())
        verify(editsMock).commit(any(), eq(editId))
        verify(commitMock).execute()
    }

    companion object {
        const val editId = "editId"
    }

}