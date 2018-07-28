package lt.boldadmin.sektor.mobile.android.publisher.test.unit


import com.google.api.services.androidpublisher.model.AppEdit
import com.nhaarman.mockito_kotlin.*
import lt.boldadmin.sektor.mobile.android.publisher.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class PublisherTest {

    @Mock
    private lateinit var propertyLoaderSpy: PropertyLoader

    @Mock
    private lateinit var editsServiceSpy: EditsService

    @Test
    fun `Publishes app to play store`() {
        val appEditStub = mock<AppEdit>()
        doReturn(mock<Properties>()).`when`(propertyLoaderSpy).load(any())
        doReturn(appEditStub).`when`(editsServiceSpy).createNewEdit(any())
        doReturn(editId).`when`(appEditStub).id

        Publisher(editsServiceSpy, propertyLoaderSpy).publish()

        verify(propertyLoaderSpy, times(3)).load(any())
        verify(editsServiceSpy).createNewEdit(any())
        verify(editsServiceSpy).uploadApk(eq(editId), any(), any())
        verify(editsServiceSpy).updateTrack(eq(editId), any(), any())
        verify(editsServiceSpy).commit(eq(editId), any())
    }

    companion object {
        const val editId = "editId"
    }

}