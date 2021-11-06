package com.nonamed1.testannotationprocessor

import com.nonamed1.annotation.ViewHolder
import com.nonamed1.annotation.ViewHolderState

@ViewHolder(layoutId = R.layout.viewholder_person)
data class PersonViewHolderState(
    override val data: Person,
    val onClickItem: (PersonViewHolderState) -> Unit
) : ViewHolderState<Person>(data)

data class Person(val name: String, val age: Int)
