package com.app.breastconnect.data
/*
*   "name": "Shore Medical Center",
      "county": "Atlantic",
      "address": "100 Medical Center Way, Somers Point, NJ 08244",
      "phone": "609-653-3484",
      "fax": "609-653-3699",
      "njceed_available": true,
      "screening_3d": true,
      "diagnostic_mammography": null,
      "breast_ultrasound": null,
      "breast_mri": null,
      "source": "NJDOH NJCEED"
* */
data class ScreeningLocation(
    val name: String,
    val county: String,
    val address: String,
    val phone: String,
    val fax: String,
    val isNJCEEDAvailable: Boolean,
    val is3DScreeningAvailable: Boolean,
    val diagnosticMammography: Boolean,
    val breastUltrasound: Boolean,
    val breastMRI: Boolean,
    val source: String
)
