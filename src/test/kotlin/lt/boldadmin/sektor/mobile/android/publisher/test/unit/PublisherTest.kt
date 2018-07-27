package lt.boldadmin.sektor.mobile.android.publisher.test.unit


import com.google.api.services.androidpublisher.model.AppEdit
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PublisherTest {

    @Mock
    private lateinit var editsServiceSpy: EditsService

    @Test
    fun `Publishes app to play store`() {
        val appEditStub = mock<AppEdit>()
        doReturn(appEditStub).`when`(editsServiceSpy).createNewEdit()
        doReturn(editId).`when`(appEditStub).id

        Publisher(editsServiceSpy).publish()

        verify(editsServiceSpy).createNewEdit()
        verify(editsServiceSpy).uploadApk(editId)
        verify(editsServiceSpy).updateTrack(editId)
        verify(editsServiceSpy).commit(editId)
    }

    companion object {
        const val editId = "editId"
    }

}