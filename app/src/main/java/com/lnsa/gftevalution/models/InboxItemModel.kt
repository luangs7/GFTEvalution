package com.lnsa.gftevalution.models

import com.lnsa.gftevalution.utils.InboxTypeEnum


data class InboxItemModel(
        var id: String = "",
        var title: String = "",
        var selected: Boolean = false,
        var type: String = "",
        var typeNotification: InboxTypeEnum?= null
)
