//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 1 October 2021

package com.example.parliamentmembersapp

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.example.parliamentmembersapp.database.AppRepository
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.network.ParliamentApi
import com.example.parliamentmembersapp.work.RefreshDataWorker
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

// This class includes periodic WorkRequest for WorkManager
class MyApp : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        fetchData()
        delayedInit()
    }

    private fun fetchData(){
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        coroutineScope?.launch {

            val dataSrc: List<MemberOfParliament> = ParliamentApi.retrofitService.getParliamentMembers()

            AppRepository.insertMember(dataSrc)

            launch(Dispatchers.Main) {
                try {
                    _response.value = "Success: Parliament members retrieved"
                } catch (e: Exception) {
                    _response.value = "Failure: ${e.message}"
                }
            }
        }
    }

    private fun setUpRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }
            .build()

        val repeatingRequest =
            PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        Timber.d("WorkManager: Periodic Work request for sync is scheduled")

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            setUpRecurringWork()
        }
    }
}