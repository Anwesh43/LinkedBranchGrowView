package com.anwesh.uiprojects.linkedbranchgrowview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.branchgrowview.BranchGrowView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BranchGrowView.create(this)
    }
}