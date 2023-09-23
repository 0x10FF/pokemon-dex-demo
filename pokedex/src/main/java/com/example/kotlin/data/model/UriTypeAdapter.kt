package com.example.kotlin.data.model

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

class UriTypeAdapter : JsonSerializer<Uri>,
    JsonDeserializer<Uri> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Uri {
        return Uri.parse(json.asString)
    }

    override fun serialize(
        src: Uri,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}