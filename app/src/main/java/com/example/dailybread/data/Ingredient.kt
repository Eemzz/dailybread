package com.example.dailybread.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(@PrimaryKey val name: String, @ColumnInfo val count: String)


