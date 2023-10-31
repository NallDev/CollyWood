package com.nalldev.collywood.domain.listener

import com.nalldev.collywood.domain.model.DetailModel

interface OnItemClickListener {
    fun onItemClick(data : DetailModel?)
}