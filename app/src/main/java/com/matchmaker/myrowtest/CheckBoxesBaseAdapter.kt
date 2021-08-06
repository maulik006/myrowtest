package com.matchmaker.myrowtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.content.ClipData.Item
import android.content.Context
import android.widget.LinearLayout


class CheckBoxesBaseAdapter(
    val context: Context,
    var list: ArrayList<String?>,
    var selectedCheckBoxes: HashMap<String, ArrayList<String>>
) : BaseAdapter() {


    fun updateList(newList: ArrayList<String?>) {
        list = newList
        notifyDataSetChanged()
    }

    fun getSelectedValue(): HashMap<String, ArrayList<String>> {
        return selectedCheckBoxes
    }

    fun addToList(position: Int, itemCode: String, itemAt: Int) {
        val selectedItem = selectedCheckBoxes.getValue(position.toString())
        selectedItem.set(itemAt - 1, itemCode)
        selectedCheckBoxes[position.toString()] = selectedItem
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): String? {
            return list.get(p0)
    }

    override fun getItemId(p0: Int): Long {
            return  p0.toLong()
    }

    override fun getView(position: Int,p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_check_box, p2, false)
        }

        //sets the text for item name and item description from the current item object
        val rootview = convertView?.findViewById<LinearLayout>(R.id.rl_root)
        val listOfRowItem = arrayListOf<String>()
        for (i in 0..(position)) {
            val itemCode = i + 1
            val checkBox = CheckBox(context)
            checkBox.tag = position
            checkBox.setText("$itemCode")
            listOfRowItem.add(i, "C$itemCode (False)")

            checkBox.setOnCheckedChangeListener { compoundButton, b ->
                val checkedPosition = compoundButton.tag as Int
                val checkedItemCode = compoundButton.text.toString().toInt()
                if (b) {
                    addToList(checkedPosition, "C"+checkedItemCode.toString() + " (True)", itemCode)
                } else {
                    addToList(checkedPosition, "C"+checkedItemCode.toString() + " (False)", itemCode)
                }
            }
            rootview?.addView(checkBox)
        }

        selectedCheckBoxes[position.toString()] = listOfRowItem

        // returns the view for the current row

        // returns the view for the current row
        return convertView!!
    }
}