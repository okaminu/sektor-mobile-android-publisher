#!/bin/sh

aapt dump badging sektor-mobile-android-release.apk | grep -Po "versionCode=\'\K[^\s\']+"
