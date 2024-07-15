package com.example.foodstand.views.base

import android.accounts.NetworkErrorException
import android.app.Application
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.foodstand.R
import com.example.foodstand.util.NetworkListener
import com.example.foodstand.util.SingleLiveEvent
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel: ViewModel() {
    private val _showErrorLiveData = SingleLiveEvent<Int>()
    val showErrorLiveData: LiveData<Int> = _showErrorLiveData

    protected fun launchRequestWithErrorHandling(
        context: CoroutineContext = EmptyCoroutineContext,
        apiCall: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope
        .launch(context){
             try{
                 apiCall()
             }catch (e: Exception){
                 when(e){
                     is HttpException ->
                         _showErrorLiveData.value = R.string.http_error
                     is NetworkErrorException,
                     is SocketTimeoutException,
                     is UnknownHostException ->
                         _showErrorLiveData.value = R.string.something_wrong
                 }
             }
    }
}