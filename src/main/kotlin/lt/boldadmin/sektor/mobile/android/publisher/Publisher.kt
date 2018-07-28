package lt.boldadmin.sektor.mobile.android.publisher

class Publisher(
    private val editsService: EditsService = EditsService(),
    private val propertyLoader: PropertyLoader = PropertyLoader()
) {

    fun publish() {
        val packageName = propertyLoader.load("publisher.properties")["PACKAGE_NAME"].toString()
        val apkPath = propertyLoader.load("publisher.properties")["APK_FILE_PATH"].toString()
        val trackType = propertyLoader.load("config.properties")["TRACK"].toString()

        val editId = editsService.createNewEdit(packageName).id
        editsService.uploadApk(editId, packageName, apkPath)
        editsService.updateTrack(editId, packageName, trackType)
        editsService.commit(editId, packageName)
    }

}