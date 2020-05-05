package com.news.media.data

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.WorkerThread
import coil.Coil
import coil.ImageLoader
import coil.api.load
import coil.request.GetRequest
import coil.request.LoadRequest
import coil.request.RequestResult
import com.news.media.api.ImageProvider
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.inject.Inject

class ImageProviderImpl @Inject constructor() : ImageProvider {
    override fun load(url: String, view: ImageView, placeholder: Drawable?) {
        placeholder?.let { holder ->
            view.load(url) {
                placeholder(holder)
            }
        } ?: view.load(url)
    }

    override fun load(drawable: Int, view: ImageView, placeholder: Drawable?) {
        placeholder?.let { holder ->
            view.load(drawable) {
                placeholder(holder)
            }
        } ?: view.load(drawable)
    }

    override fun load(file: File, view: ImageView, placeholder: Drawable?) {
        placeholder?.let { holder ->
            view.load(file) {
                placeholder(holder)
            }
        } ?: view.load(file)
    }

    override fun load(
        context: Context,
        url: String,
        target: () -> Drawable,
        placeholder: Drawable?
    ) {
        val request =
            LoadRequest.Builder(context)
                .data(url)
                .target {
                    target()
                }
                .build()
        Coil.execute(request)
    }

    override suspend fun load(context: Context, url: String, placeholder: Drawable?): Drawable? {
        val request = GetRequest.Builder(context)
            .data(url)
            .build()
        return Coil.execute(request).drawable
    }
}