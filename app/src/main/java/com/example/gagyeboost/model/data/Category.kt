package com.example.gagyeboost.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gagyeboost.common.EXPENSE

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "category_name")
    var categoryName: String,

    @ColumnInfo(name = "emoji")
    var emoji: String,

    @ColumnInfo(name = "money_type")
    var moneyType: Byte

) {
    constructor() : this(0, "", "", EXPENSE)
}

val nothingEmoji = "❌"
val emojiList = listOf(
    "🥰",
    "🐱",
    "🐔",
    "🍎",
    "🍉",
    "🛴",
    "🎠",
    "🏖",
    "📱",
    "💊",
    "❤️",
    "\uD83C\uDF5A",
    "\uD83C\uDFBE",
    "\uD83D\uDE8C",
    "🐶",
    "⚽",
    "🏀",
    "🏈",
    "⚾",
    "🥎",
    "🏐",
    "🏉",
    "🎱",
    "🚗",
    "🚙",
    "🚎",
    "🚛",
    "🚲",
    "🏫",
    "🌆",
    "✈",
    "🎇"
)
