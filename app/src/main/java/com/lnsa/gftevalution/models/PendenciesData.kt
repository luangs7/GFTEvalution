package com.lnsa.gftevalution.models

import com.lnsa.gftevalution.utils.InboxTypeEnum

data class PendenciesData(
    var id: String = "",
    var pendencyId: String = "",
    var startTime: String = "",
    var complement: String = "",
    var typeNotification: InboxTypeEnum? = null
)

inline fun PendenciesData.getInboxPendencyDataServiceItemModel(): InboxData {
    return InboxData(
        id = id,
        pendencyId = pendencyId,
        complement = complement,
        solictAt = startTime,
        typeNotification = typeNotification
    )
}
