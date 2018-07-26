package lt.boldadmin.sektor.mobile.android.publisher

import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppEdit
import com.google.api.services.androidpublisher.model.Track
import lt.boldadmin.sektor.mobile.android.publisher.factory.AndroidPublisherFactory
import lt.boldadmin.sektor.mobile.android.publisher.factory.TrackReleaseFactory

class EditsService(
    private val edits: AndroidPublisher.Edits = AndroidPublisherFactory().create().edits(),
    private val propertyLoader: PropertyLoader = PropertyLoader(),
    private val apkLoader: ApkLoader = ApkLoader(),
    private val track: Track = Track(),
    private val trackReleaseFactory: TrackReleaseFactory = TrackReleaseFactory()
) {

    fun createNewEdit(): AppEdit =
        edits.insert(
            propertyLoader.load("publisher.properties")["PACKAGE_NAME"].toString(),
            null
        ).execute()

    fun uploadApk(editId: String) {
        edits
            .apks()
            .upload(
                propertyLoader.load("publisher.properties")["PACKAGE_NAME"].toString(),
                editId,
                apkLoader.loadApk()
            )
            .execute()
        println("Apk successfully uploaded")
    }

    fun updateTrack(editId: String) {
        edits
            .tracks()
            .update(
                propertyLoader.load("publisher.properties")["PACKAGE_NAME"].toString(),
                editId,
                propertyLoader.load("config.properties")["TRACK"].toString(),
                track.setReleases(listOf(trackReleaseFactory.create()))
            )
            .execute()
        println("Track successfully updated")
    }

    fun commit(editId: String) {
        edits
            .commit(propertyLoader.load("publisher.properties")["PACKAGE_NAME"].toString(), editId)
            .execute()
        println("App eddit $editId successfully committed")
    }
}