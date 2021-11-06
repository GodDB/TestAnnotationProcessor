package com.nonamed1.testannotationprocessor

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.nonamed1.annotation.ViewHolder
import com.nonamed1.annotation.ViewHolderState
import com.nonamed1.testannotationprocessor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.rv.apply {
                adapter = TestAdapter().apply { this.setItems(createDummyDatas()) }
            }
    }

    private fun createDummyDatas() : List<PersonViewHolderState> =
        mutableListOf(
            PersonViewHolderState(Person("a1",1), {
                Log.d("godgod", "$it")
            }),
            PersonViewHolderState(Person("a2",2), { }),
            PersonViewHolderState(Person("a3",3), { }),
            PersonViewHolderState(Person("a4",4), { }),
            PersonViewHolderState(Person("a5",5), { }),
            PersonViewHolderState(Person("a6",6), { }),
            PersonViewHolderState(Person("a7",7), { }),
            PersonViewHolderState(Person("a8",8), { }),
            PersonViewHolderState(Person("a9",9), { }),
            PersonViewHolderState(Person("a10",10), { })
        )
}




