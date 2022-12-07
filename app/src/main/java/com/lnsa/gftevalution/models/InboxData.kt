package com.lnsa.gftevalution.models

import com.lnsa.gftevalution.utils.InboxTypeEnum

data class InboxData(
        var id: String = "",
        var pendencyId: String = "",
        var complement: String = "",
        var title: String = "",
        var selected: Boolean = false,
        var solictAt: String = "",
        var typeNotification: InboxTypeEnum? = null
)

inline fun InboxItemModel.getInboxNotificationDataServiceItemModel(): InboxData {
    return InboxData(id = id,
            title = title,
            selected = selected,
            typeNotification = typeNotification
    )
}
