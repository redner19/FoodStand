package com.example.foodstand.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object FragmentExt {
    fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
        object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {
            private var binding: T? = null

            init{
                this@viewLifecycle
                    .viewLifecycleOwnerLiveData
                    .observe(this@viewLifecycle) { owner ->
                        owner?.lifecycle?.addObserver(this)
                    }
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                binding = null
            }

            override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
                return this.binding ?: error("Called before onCreateView or after onDestroy.")
            }

            override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
               this.binding = value
            }
        }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>){
        observe(lifecycleOwner, object: Observer<T> {
            override fun onChanged(value: T) {
                removeObserver(this)
                observer.onChanged(value)
            }
        })
    }
}