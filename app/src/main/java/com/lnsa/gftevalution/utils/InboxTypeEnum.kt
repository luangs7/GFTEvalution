package com.lnsa.gftevalution.utils

sealed class InboxTypeEnum

class NOTIFICATION:InboxTypeEnum()
class PENDENCY:InboxTypeEnum()
class MESSAGE:InboxTypeEnum()

