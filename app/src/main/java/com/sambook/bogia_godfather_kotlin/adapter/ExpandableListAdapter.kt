package com.sambook.bogia_godfather_kotlin.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.sambook.bogia_godfather_kotlin.R


class ExpandableListAdapter(_context: Context?, _listDataHeader: List<String>, _listChildData: HashMap<String, List<String>>) : BaseExpandableListAdapter() {

    private val _context: Context? = _context
    private val _listDataHeader: List<String>? = _listDataHeader // header titles

    // child data in format of header title, child title
    private var _listDataChild: HashMap<String, List<String>> = _listChildData

    override fun getGroupCount(): Int {
        return this._listDataHeader?.size ?: 0
    }

    override fun getChildrenCount(p0: Int): Int {
        if (this._listDataChild?.get(this._listDataHeader?.get(p0)) == null){
            return 0
        }
        return this._listDataChild?.get(this._listDataHeader?.get(p0))!!.size
    }

    override fun getGroup(p0: Int): Any {
        return this._listDataHeader?.get(p0) ?: 0
    }

    override fun getChild(p0: Int, p1: Int): String? {
        return this._listDataChild?.get(this._listDataHeader?.get(p0))?.get(p1)
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, p1: Boolean, convertView: View?, p3: ViewGroup?): View {
        var view = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (view == null) {
            val infalInflater = _context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = infalInflater.inflate(R.layout.list_group, null)
        }
        val lblListHeader = view?.findViewById(R.id.lblListHeader) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text = headerTitle
        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {
            val infalInflater = _context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = infalInflater.inflate(R.layout.list_item, null)
        }

        val txtListChild = view?.findViewById(R.id.lblListItem) as TextView

        txtListChild.text = getChild(groupPosition, childPosition)
        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}