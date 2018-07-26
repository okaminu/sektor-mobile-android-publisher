package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport

fun createHttpTransport(): NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()