package com.pkj.learn.asshopping

import androidx.lifecycle.Observer

/**
 * @author Pankaj Jangid
 */
open class Event<out T>(private val content:T) {

    var hasBeenHandled = false
        private set


    fun getContentIfNotHandled() : T?{
        return if(hasBeenHandled) {
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content

}

class EventObserver<T>(private val onEventUnhandledContent:(T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }

}