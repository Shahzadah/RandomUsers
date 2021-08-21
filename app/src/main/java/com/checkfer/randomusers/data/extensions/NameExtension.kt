package com.checkfer.randomusers.data.extensions

import com.checkfer.randomusers.data.model.Name

fun Name.formattedName() = "${this.title} ${this.first} ${this.last}"
