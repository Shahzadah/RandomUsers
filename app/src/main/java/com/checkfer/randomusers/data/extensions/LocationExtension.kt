package com.checkfer.randomusers.data.extensions

import com.checkfer.randomusers.data.model.Location

fun Location.formattedLocation() = "${this.city}, ${this.country}"
