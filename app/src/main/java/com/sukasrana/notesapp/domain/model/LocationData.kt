package com.sukasrana.notesapp.domain.model

import com.google.android.gms.maps.model.LatLng

data class LocationData(
    val latLng: LatLng = LatLng(0.0, 0.0)
)