package lt.boldadmin.sektor.mobile.android.publisher

class Publisher(private val editsService: EditsService = EditsService()) {

    fun publish() {
        val editId = editsService.createNewEdit().id

        editsService.uploadApk(editId)
        editsService.updateTrack(editId)
        editsService.commit(editId)
    }

}