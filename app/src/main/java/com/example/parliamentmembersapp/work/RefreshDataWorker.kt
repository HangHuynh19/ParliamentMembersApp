//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 8 October 2021

package com.example.parliamentmembersapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.parliamentmembersapp.database.AppRepository
import retrofit2.HttpException
import timber.log.Timber

// This class contains the tasks (in this case is fetching all the members' detail form the internet)
// that will be run on the background

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.example.parliamentmembersapp.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val repository = AppRepository

        try {
            repository.refreshMembersData()
            Timber.d("WorkManager: Work request for sync is running")
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }

}