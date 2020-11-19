package com.mikeescom.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import com.mikeescom.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<FragmentContainerView>(R.id.list_fragment) != null) {
            val listFragment = ListFragment()
            val detailFragment = DetailFragment()

            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            transaction.add(R.id.list_fragment, listFragment,"LIST_FRAG")
            transaction.add(R.id.detail_fragment, detailFragment,"DETAIL_FRAG")

            transaction.commit()
        }
    }
}