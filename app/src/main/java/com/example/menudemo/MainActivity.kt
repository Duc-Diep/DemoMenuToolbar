package com.example.menudemo

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener,NavigationView.OnNavigationItemSelectedListener {
    var actionMode: ActionMode? = null
    lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up tool bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.title = "   Tiêu đề action bar"

        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setLogo(R.drawable.ic_baseline_person_24)   //Icon muốn hiện thị
        actionBar?.setDisplayUseLogoEnabled(true)

        drawerLayout = findViewById(R.id.drawer_layout)
        var toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //set up navigation view
        navigation_view.setNavigationItemSelectedListener(this)




        registerForContextMenu(btn_context_menu)

        btn_contextual.setOnLongClickListener { view ->
            when (actionMode) {
                null -> {
                    actionMode = startActionMode(actionModeCallback)
                    view.isSelected = true
                    true
                }
                else -> false
            }
        }

        btn_popup.setOnClickListener {
            showPopup(it)
        }

        btn_listview.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListViewActionMode::class.java))
        }
    }

    //create popup
    private fun showPopup(view: View) {
        var popupMenu = PopupMenu(this, view)
        var inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(this)
        popupMenu.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_popup_1 -> {
                Toast.makeText(this, "Popup 1", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_popup_2 -> {
                Toast.makeText(this, "Popup 2", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_popup_3 -> {
                Toast.makeText(this, "Popup 3", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }

    }

    // create option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            android.R.id.home-> Toast.makeText(this, "Item: Back Action Bar", Toast.LENGTH_SHORT).show()
            R.id.menu_home -> Toast.makeText(this, "Item: Home", Toast.LENGTH_SHORT).show()
            R.id.menu_setting -> Toast.makeText(this, "Item: Setting", Toast.LENGTH_SHORT).show()
            R.id.menu_profile -> Toast.makeText(this, "Item: Profile", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    //create context menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
        menu?.setHeaderTitle("Chọn màu")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_context_1 -> Toast.makeText(this, "Item: Red", Toast.LENGTH_SHORT).show()
            R.id.menu_context_2 -> Toast.makeText(this, "Item: Blue", Toast.LENGTH_SHORT).show()
            R.id.menu_context_3 -> Toast.makeText(this, "Item: Yellow", Toast.LENGTH_SHORT).show()
            R.id.menu_context_31 -> Toast.makeText(this, "Item: Yellow 1", Toast.LENGTH_SHORT)
                .show()
            R.id.menu_context_32 -> Toast.makeText(this, "Item: Yellow 2", Toast.LENGTH_SHORT)
                .show()

        }
        return super.onContextItemSelected(item)
    }

    //create contextual action mode

    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.contextual_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_delete -> {
                    Toast.makeText(this@MainActivity, "Action delete", Toast.LENGTH_SHORT).show()
                    mode.finish()
                    true
                }
                R.id.action_share -> {
                    Toast.makeText(this@MainActivity, "Action share", Toast.LENGTH_SHORT).show()
                    mode.finish()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> Toast.makeText(this@MainActivity, "Nav Home", Toast.LENGTH_SHORT).show()
            R.id.nav_profile -> Toast.makeText(this@MainActivity, "Nav Profile", Toast.LENGTH_SHORT).show()
            R.id.nav_setting -> Toast.makeText(this@MainActivity, "Nav Setting", Toast.LENGTH_SHORT).show()
            R.id.nav_account -> Toast.makeText(this@MainActivity, "Nav Account", Toast.LENGTH_SHORT).show()
            R.id.nav_account_1 -> Toast.makeText(this@MainActivity, "Nav Account 1", Toast.LENGTH_SHORT).show()
            R.id.nav_account_2 -> Toast.makeText(this@MainActivity, "Nav Account 2", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

}