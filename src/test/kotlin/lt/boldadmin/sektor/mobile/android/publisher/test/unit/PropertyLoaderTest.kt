package lt.boldadmin.sektor.mobile.android.publisher.test.unit

import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader
import org.junit.Assert.assertNotNull
import org.junit.Test

class PropertyLoaderTest {

    @Test
    fun load() {
        assertNotNull(PropertyLoader().load("config.properties"))
    }
}