#!/bin/sh

$AAPT_LOCATION dump badging sektor-mobile-android-release.apk | grep -Po "versionName=\'\K[^\s\']+"
