package com.lnsa.gftevalution.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lnsa.gftevalution.models.*
import com.lnsa.gftevalution.utils.InboxDataUtils
import com.lnsa.gftevalution.utils.MESSAGE
import com.lnsa.gftevalution.utils.NOTIFICATION
import com.lnsa.gftevalution.utils.PENDENCY
import kotlinx.coroutines.delay
import java.util.*
import kotlin.collections.ArrayList

class InboxDialogViewModel : ViewModel() {

    var ids: List<String>? = null

    fun separateData(inboxList: InboxItemModelDataTypes) {
        var pendencyList = inboxList.pendencies
        var notificationList = inboxList.notifications

        InboxDataUtils.ref.cleanItems()
        notificationList.let{
            it.forEach {
                if (it.type.toLowerCase().equals("notificações")) {
                    it.typeNotification = NOTIFICATION()
                    InboxDataUtils.ref.transationsList.add(it.getInboxNotificationDataServiceItemModel())
                } else if (it.type.toLowerCase().equals("mensagens")) {
                    it.typeNotification = MESSAGE()
                    InboxDataUtils.ref.newsList.add(it.getInboxNotificationDataServiceItemModel())
                }
            }
        }

        pendencyList.let {
            it.forEach {
                it.typeNotification = PENDENCY()
                InboxDataUtils.ref.reminderList.add(it.getInboxPendencyDataServiceItemModel())
            }
        }


        InboxDataUtils.ref.getAllItemsTogether()
        var allItemsTogether = InboxDataUtils.ref.allItemsOfList

        val calendarMaxNow = Calendar.getInstance()
        val calendarMinNow = Calendar.getInstance()
        calendarMinNow.add(Calendar.DATE, -7);


        var whitinSevenDays =
            getDataWithinSevenDays(allItemsTogether, calendarMinNow, calendarMaxNow)

        var beyondSevenDays = getDataBeyondSevenDays(allItemsTogether, calendarMinNow,calendarMaxNow)

        if (whitinSevenDays.isNotEmpty()) {
            InboxDataUtils.ref.recentNotificationList.addAll(whitinSevenDays)
        }

        if (beyondSevenDays.isNotEmpty()) {
            InboxDataUtils.ref.oldNotificationList.addAll(beyondSevenDays)
        }

        InboxDataUtils.ref.sortAll()
    }

    private fun getDataBeyondSevenDays(
            allItemsTogether: ArrayList<InboxData>,
            calendarMinNow: Calendar,
            calendarMaxNow: Calendar
    ): ArrayList<InboxData>   {
        val newArray = arrayListOf<InboxData>()

        for (it in allItemsTogether) {
            InboxDataUtils.ref.setSeenIcon(it)
            val calendar = it.solictAt

            var isBeyond = isDateInBeforeIncludingEndPoints(
                    Date(calendarMinNow.timeInMillis),
                    Date(calendarMaxNow.timeInMillis),
                Calendar.getInstance().time
            )

            var isBetween = isDateInBetweenIncludingEndPoints(
                    Date(calendarMinNow.timeInMillis),
                    Date(calendarMaxNow.timeInMillis),
                Calendar.getInstance().time
            )

            if (isBeyond && isBetween.not()) {
                newArray.add(it)
            }
        }
        return newArray
    }

    private fun getDataWithinSevenDays(
            allItemsTogether: ArrayList<InboxData>,
            calendarMinNow: Calendar,
            calendarMaxNow: Calendar
    ): ArrayList<InboxData> {
        val newArray = arrayListOf<InboxData>()

        for (it in allItemsTogether) {
            val calendar = it.solictAt

            var isBetween = isDateInBetweenIncludingEndPoints(
                    Date(calendarMinNow.timeInMillis),
                    Date(calendarMaxNow.timeInMillis),
                    Calendar.getInstance().time
            )
            if (isBetween) {
                newArray.add(it)
            }
        }

        return newArray
    }

    private fun isDateInBetweenIncludingEndPoints(min: Date?, max: Date?, date: Date): Boolean {
        return date.after(min)
    }

    private fun isDateInBeforeIncludingEndPoints(min: Date?, max: Date?, date: Date): Boolean {
        return date.before(min)
    }
}