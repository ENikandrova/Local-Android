package com.local.app.repository.photo

import android.content.ContentResolver
import com.local.app.api.RetrofitClient
import com.local.app.api.requests.UploadFileBody
import com.local.app.data.photo.PhotoInDir
import io.reactivex.Single

class UploadPhotoRepositoryImpl(val retrofitClient: RetrofitClient,
                                val contentResolver: ContentResolver) : UploadPhotoRepository {

    override var photos = ArrayList<PhotoInDir>()

    override fun uploadImageFile(fileDir: String): Single<PhotoInDir> {
        photos.add(PhotoInDir(fileDir))
        //        val file = File(fileDir)
        val body = UploadFileBody(fileDir, contentResolver)
        return retrofitClient.api
            .loadImage(body.fileType, body.image)
            .doOnError {
                photos.forEach {
                    if (it.uri == fileDir) {
                        it.isError = true
                    }
                }
            }
            .map { photoEntity ->
                photos.forEach {
                    if (it.uri == fileDir) {
                        it.hash = photoEntity.hash
                        return@map it
                    }
                }
                return@map PhotoInDir("")
            }

    }
}