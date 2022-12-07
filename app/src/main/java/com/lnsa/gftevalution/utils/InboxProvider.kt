package com.lnsa.gftevalution.utils

import com.lnsa.gftevalution.models.InboxData
import com.lnsa.gftevalution.models.InboxItemModel
import com.lnsa.gftevalution.models.PendenciesData

object InboxProvider {
    fun getInboxModel(): ArrayList<InboxItemModel>  {
        val items: ArrayList<InboxItemModel> = arrayListOf()
        for (i in 0 until 20){
            items.add(InboxItemModel(type= "mensagens"))
        }
        for (i in 0 until 40){
            items.add(InboxItemModel(type= "notificações"))
        }
        return items
    }

    fun getPendencies(): ArrayList<PendenciesData>  {
        val items: ArrayList<PendenciesData> = arrayListOf()
        for (i in 0 until 40){
            items.add(PendenciesData())
        }
        return items
    }
}