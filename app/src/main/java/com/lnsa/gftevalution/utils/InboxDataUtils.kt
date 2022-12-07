package com.lnsa.gftevalution.utils

import com.lnsa.gftevalution.models.InboxData
import java.text.DateFormatSymbols
import java.util.*
import kotlin.system.measureTimeMillis

class InboxDataUtils {

    companion object{
        var ref = InboxDataUtils()
    }


    var allItemsOfList = arrayListOf<InboxData>()
    var recentNotificationList = arrayListOf<InboxData>()
    var oldNotificationList = arrayListOf<InboxData>()
    var transationsList = arrayListOf<InboxData>()
    var newsList = arrayListOf<InboxData>()
    var reminderList = arrayListOf<InboxData>()

    fun sortAll(){
        val time = measureTimeMillis {

            transationsList.sortByDescending { it.solictAt }
            newsList.sortByDescending { it.solictAt }
            reminderList.sortByDescending { it.solictAt }
        }
    }

    fun getAllItemsTogether(){
        val time = measureTimeMillis {
            allItemsOfList.clear()
            allItemsOfList.addAll(transationsList)
            allItemsOfList.addAll(newsList)
            allItemsOfList.addAll(reminderList)

            allItemsOfList.sortByDescending { it.solictAt }
        }
    }


    fun cleanItems(){
        recentNotificationList.clear()
        oldNotificationList.clear()
        transationsList.clear()
        newsList.clear()
        reminderList.clear()
    }

    fun setSeenIcon(element: InboxData){
        if(recentNotificationList.indexOf(element) > 0) {
            val index = recentNotificationList.indexOf(element)
            recentNotificationList[index] = element
        }
        if(oldNotificationList.indexOf(element) > 0){
            val index = oldNotificationList.indexOf(element)
            oldNotificationList[index] = element
        }
        if(transationsList.indexOf(element) > 0){
            val index = transationsList.indexOf(element)
            transationsList[index] = element
        }
        if(newsList.indexOf(element) > 0){
            val index = newsList.indexOf(element)
            newsList[index] = element
        }
        if(reminderList.indexOf(element) > 0){
            val index = reminderList.indexOf(element)
            reminderList[index] = element
        }
    }
}