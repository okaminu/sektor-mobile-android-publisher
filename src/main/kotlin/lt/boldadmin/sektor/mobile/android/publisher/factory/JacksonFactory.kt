package lt.boldadmin.sektor.mobile.android.publisher.factory

import com.google.api.client.json.jackson2.JacksonFactory

fun createJacksonInstance(): JacksonFactory = JacksonFactory.getDefaultInstance()