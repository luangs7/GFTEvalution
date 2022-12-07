package com.lnsa.gftevalution.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.lnsa.gftevalution.R
import com.lnsa.gftevalution.models.InboxData
import com.lnsa.gftevalution.utils.InboxDataUtils
import java.util.*
import kotlin.collections.ArrayList

class InboxListOfNews @JvmOverloads constructor(
    context: Context, var attrs: AttributeSet? = null, defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        context.let {
            val inflater: LayoutInflater =
                    it.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            inflater.inflate(R.layout.inbox_home_list_of_news, this, true)
        }
    }

    fun sortList(list: ArrayList<InboxData>){
        Collections.sort(list, object : Comparator<InboxData> {
            override fun compare(first: InboxData?, second: InboxData?): Int {
                var dateFirst = first?.solictAt
                var dateSecond = second?.solictAt

                return dateFirst?.compareTo(dateSecond.orEmpty()) ?: 0
            }

        })
        list.reverse()
    }

    fun setData() {
        val recentList = arrayListOf<InboxData>()
        sortList(InboxDataUtils.ref.recentNotificationList)
        recentList.addAll(InboxDataUtils.ref.recentNotificationList)

        val oldList = arrayListOf<InboxData>()
        sortList(InboxDataUtils.ref.oldNotificationList)
        oldList.addAll(InboxDataUtils.ref.oldNotificationList)

        val transactionList = arrayListOf<InboxData>()
        sortList(InboxDataUtils.ref.transationsList)
        transactionList.addAll(InboxDataUtils.ref.transationsList)

        val newsList = arrayListOf<InboxData>()
        sortList(InboxDataUtils.ref.newsList)
        newsList.addAll(InboxDataUtils.ref.newsList)

        val reminderList = arrayListOf<InboxData>()
        sortList(InboxDataUtils.ref.reminderList)
        reminderList.addAll(InboxDataUtils.ref.reminderList)
    }

}