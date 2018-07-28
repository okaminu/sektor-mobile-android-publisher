package lt.boldadmin.sektor.mobile.android.publisher.test.unit

import com.google.api.client.http.FileContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Insert
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
import lt.boldadmin.sektor.mobile.android.publisher.factory.TrackReleaseFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EditsServiceTest {

    @Mock
    private lateinit var editsMockSpy: AndroidPublisher.Edits

    @Mock
    private lateinit var apkLoaderSpy: ApkLoader

    @Mock
    private lateinit var trackSpy: Track

    @Mock
    private lateinit var trackFactorySpy: TrackReleaseFactory

    private lateinit var editsService: EditsService

    @Before
    fun setUp() {
        editsService = EditsService(editsMockSpy, apkLoaderSpy, trackSpy, trackFactorySpy)
    }

    @Test
    fun `Creates a new Edit`() {
        val insertSpy = mock<Insert>()
        val editDummy = mock<AppEdit>()
        doReturn(insertSpy).`when`(editsMockSpy).insert(packageName, null)
        doReturn(editDummy).`when`(insertSpy).execute()

        val newEdit = editsService.createNewEdit(packageName)

        assertEquals(editDummy, newEdit)
        verify(editsMockSpy).insert(packageName, null)
        verify(insertSpy).execute()
    }

    @Test
    fun `Uploads apk to Edit`() {
        val apksSpy = mock<AndroidPublisher.Edits.Apks>()
        val uploadSpy = mock<AndroidPublisher.Edits.Apks.Upload>()
        val apkDummy = mock<FileContent>()
        doReturn(apksSpy).`when`(editsMockSpy).apks()
        doReturn(uploadSpy).`when`(apksSpy).upload(any(), eq(editId), any())
        doReturn(apkDummy).`when`(apkLoaderSpy).load(apkPath)
        doReturn(mock<Apk>()).`when`(uploadSpy).execute()

        editsService.uploadApk(editId, packageName, apkPath)

        verify(editsMockSpy).apks()
        verify(apksSpy).upload(packageName, editId, apkDummy)
        verify(apkLoaderSpy).load(any())
        verify(uploadSpy).execute()
    }

    @Test
    fun `Updates Track info`() {
        val tracksSpy = mock<Tracks>()
        val updateSpy = mock<Update>()
        val trackDummy = mock<Track>()
        doReturn(tracksSpy).`when`(editsMockSpy).tracks()
        doReturn(updateSpy).`when`(tracksSpy).update(any(), eq(editId), any(), any())
        doReturn(mock<Track>()).`when`(updateSpy).execute()
        doReturn(mock<TrackRelease>()).`when`(trackFactorySpy).create()
        doReturn(trackDummy).`when`(trackSpy).releases = any()

        editsService.updateTrack(editId, packageName, trackType)

        verify(editsMockSpy).tracks()
        verify(tracksSpy).update(eq(packageName), eq(editId), eq(trackType), eq(trackDummy))
        verify(trackFactorySpy).create()
        verify(trackSpy).releases = any()
        verify(updateSpy).execute()
    }

    @Test
    fun `Commits Track changes`() {
        val commitSpy = mock<Commit>()
        doReturn(commitSpy).`when`(editsMockSpy).commit(any(), eq(editId))
        doReturn(mock<AppEdit>()).`when`(commitSpy).execute()

        editsService.commit(editId, packageName)

        verify(editsMockSpy).commit(packageName, editId)
        verify(commitSpy).execute()
    }

    companion object {
        const val editId = "editId"
        const val packageName = "packageName"
        const val apkPath = "aphPath"
        const val trackType = "trackType"
    }

}