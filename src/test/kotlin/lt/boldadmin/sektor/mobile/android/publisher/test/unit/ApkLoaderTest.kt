package lt.boldadmin.sektor.mobile.android.publisher.test.unit

import com.google.api.client.http.FileContent
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import lt.boldadmin.sektor.mobile.android.publisher.ApkLoader
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.File
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ApkLoaderTest {

    @Mock
    private lateinit var propertySpy: PropertyLoader

    @Test
    fun `Loads apk file`() {
        val propertiesStub = mock<Properties>()
        doReturn(propertiesStub).`when`(propertySpy).load(any())
        doReturn(type).doReturn(path).`when`(propertiesStub)[any()]

        val apk = ApkLoader(propertySpy).load(path)

        val expectedApk = FileContent(type, File(path))
        assertEquals(expectedApk.file, apk.file)
        assertEquals(expectedApk.type, apk.type)

    }

    companion object {
        const val type = "type"
        const val path = "path"
    }

}