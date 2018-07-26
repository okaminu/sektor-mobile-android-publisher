#!/bin/sh

aapt dump badging sektor-mobile-android-release.apk | grep -Po "versionName=\'\K[^\s\']+"
