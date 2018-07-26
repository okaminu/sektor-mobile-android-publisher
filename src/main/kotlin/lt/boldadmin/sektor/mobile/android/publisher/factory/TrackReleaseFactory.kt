package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.services.androidpublisher.model.TrackRelease
import lt.boldadmin.sektor.mobile.android.publisher.LocalizedTextBuilder
import lt.boldadmin.sektor.mobile.android.publisher.PropertyLoader

class TrackReleaseFactory(
    private val trackRelease: TrackRelease = TrackRelease(),
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val localizedTextBuilder: LocalizedTextBuilder = LocalizedTextBuilder()
) {

    fun create(): TrackRelease =
        trackRelease
            .setName(propertyLoader.load("config.properties")["VERSION_NAME"].toString())
            .setVersionCodes(listOf(propertyLoader.load("config.properties")["VERSION_CODE"].toString().toLong()))
            .setStatus(completedStatus)
            .setReleaseNotes(listOf(localizedTextBuilder.build()))


    companion object {
        const val completedStatus = "completed"
    }

}