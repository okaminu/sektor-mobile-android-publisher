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
    private lateinit var editsServiceMock: EditsService

    @Test
    fun `Publishes app to play store`() {
        val appEditMock = mock<AppEdit>()
        doReturn(appEditMock).`when`(editsServiceMock).createNewEdit()
        doReturn(editId).`when`(appEditMock).id

        Publisher(editsServiceMock).publish()

        verify(editsServiceMock).createNewEdit()
        verify(editsServiceMock).uploadApk(editId)
        verify(editsServiceMock).updateTrack(editId)
        verify(editsServiceMock).commit(editId)
    }

    companion object {
        const val editId = "editId"
    }

}