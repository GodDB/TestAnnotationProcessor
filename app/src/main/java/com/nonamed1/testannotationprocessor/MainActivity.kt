package com.nonamed1.testannotationprocessor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nonamed1.annotation.ViewHolder
import com.nonamed1.annotation.ViewHolderState

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

@ViewHolder(R.layout.viewholder_person)
data class PersonViewHolderState(override val data: Person) : ViewHolderState<Person>(data) {
    override fun getBindingDataId(): Int = BR.data

}

data class Person(val name : String, val age : Int, val onClickItem : () -> Unit)