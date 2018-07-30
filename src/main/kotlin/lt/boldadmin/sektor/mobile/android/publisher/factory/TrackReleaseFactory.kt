package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.services.androidpublisher.model.TrackRelease
import lt.boldadmin.sektor.mobile.android.publisher.EnvVariablesLoader

class TrackReleaseFactory(
    private val trackRelease: TrackRelease = TrackRelease(),
    private val localizedTextFactory: LocalizedTextFactory = LocalizedTextFactory(),
    private val envLoader: EnvVariablesLoader = EnvVariablesLoader()
) {

    fun create(): TrackRelease =
        trackRelease
            .setName(envLoader.get("SEKTOR_ANDROID_VERSION_NAME"))
            .setVersionCodes(listOf(envLoader.get("SEKTOR_ANDROID_VERSION_CODE").toLong()))
            .setStatus(completedStatus)
            .setReleaseNotes(listOf(localizedTextFactory.create()))

    companion object {
        const val completedStatus = "completed"
    }

}