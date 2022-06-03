package com.ecommerce.api.models

import org.bson.types.ObjectId

class TempProduct (
    var objectid:String = ObjectId().toString(),
    var reqQty:Int
)