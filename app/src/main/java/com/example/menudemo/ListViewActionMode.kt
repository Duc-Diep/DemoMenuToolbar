package com.example.menudemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlinx.android.synthetic.main.activity_list_view_action_mode.*

class ListViewActionMode : AppCompatActivity() {
    lateinit var listColor: ArrayList<String>
    lateinit var colorAdapter: ColorAdapter
    var index = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_action_mode)
        initAdapter()
    }

    private fun initAdapter() {
        listColor = arrayListOf("Red", "Yellow", "Blue", "Orange", "Purple", "Pink")
        colorAdapter = ColorAdapter(listColor, this)
        rcv_colors.adapter = colorAdapter
        rcv_colors.choiceMode = AbsListView.CHOICE_MODE_MULTIPLE_MODAL
        rcv_colors.setMultiChoiceModeListener(multiChoiceModeListener)


    }

    var multiChoiceModeListener = object : AbsListView.MultiChoiceModeListener {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.contextual_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when(item?.itemId){
                R.id.action_delete-> {
                    colorAdapter.removeItem(index)
                    mode?.finish()
                    return true
                }
                else -> return false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {

        }

        override fun onItemCheckedStateChanged(
            mode: ActionMode?,
            position: Int,
            id: Long,
            checked: Boolean
        ) {
            mode?.title = "${listColor[position]}"
            index = position
        }

    }
}