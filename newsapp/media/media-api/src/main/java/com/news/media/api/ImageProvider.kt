package com.news.media.api

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import java.io.File

/**
 * Provides an API layer to retrieving images.
 */
interface ImageProvider {

    /**
     * Loads url into view
     * @param url: Url to load from
     * @param view: The imageview to load into
     * @param placeholder: Placeholder image
     */
    fun load(url: String, view: ImageView, placeholder: Drawable? = null)

    /**
     * Loads drawable into view
     * @param drawable: Drawable to load
     * @param view: Imageview to load into
     * @param placeholder: Placeholder image
     */
    fun load(drawable: Int, view: ImageView, placeholder: Drawable? = null)

    /**
     * Loads file into imageview
     * @param file: File to load from
     * @param view: Imageveiw to load into
     * @param placeholder: Placeholder image
     */
    fun load(file: File, view: ImageView, placeholder: Drawable? = null)

    /**
     * Loads url into a target
     * @param context: Context
     * @param url: Url to load
     * @param target: Target lambda of what to do with drawable
     * @param placeholder: Placeholder image
     */
    fun load(context: Context, url: String, target: () -> Drawable, placeholder: Drawable? = null)

    /**
     * Loads url and returns a drawable
     * @param context: Context
     * @param url: Url to load
     * @param placeholder: Placeholder image
     * @return [Drawable]
     */
    suspend fun load(context: Context, url: String, placeholder: Drawable? = null): Drawable?
}