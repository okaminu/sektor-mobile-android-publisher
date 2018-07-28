package lt.boldadmin.sektor.mobile.android.publisher

import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppEdit
import com.google.api.services.androidpublisher.model.Track
import lt.boldadmin.sektor.mobile.android.publisher.factory.AndroidPublisherFactory
import lt.boldadmin.sektor.mobile.android.publisher.factory.TrackReleaseFactory
import java.util.logging.Logger

class EditsService(
    private val edits: AndroidPublisher.Edits = AndroidPublisherFactory().create().edits(),
    private val apkLoader: ApkLoader = ApkLoader(),
    private val track: Track = Track(),
    private val trackReleaseFactory: TrackReleaseFactory = TrackReleaseFactory()
) {

    fun createNewEdit(packageName: String): AppEdit = edits.insert(packageName, null).execute()

    fun uploadApk(editId: String, packageName: String, apkPath: String) {
        edits
            .apks()
            .upload(packageName, editId, apkLoader.load(apkPath))
            .execute()

        log.info("Apk successfully uploaded to Edit $editId")
    }

    fun updateTrack(editId: String, packageName: String, trackType: String) {
        edits
            .tracks()
            .update(
                packageName,
                editId,
                trackType,
                track.setReleases(listOf(trackReleaseFactory.create()))
            )
            .execute()

        log.info("Track $editId successfully updated")
    }

    fun commit(editId: String, packageName: String) {
        edits
            .commit(packageName, editId)
            .execute()

        log.info("App Edit $editId successfully committed")
    }

    companion object {
        val log: Logger = Logger.getLogger(EditsService::class.java.name)
    }
}