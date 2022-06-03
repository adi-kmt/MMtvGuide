package com.example.mmtvguide.common_adapters

import android.view.View

interface ItemClickCallback<T> {
    fun onItemClick(Data: T)
}