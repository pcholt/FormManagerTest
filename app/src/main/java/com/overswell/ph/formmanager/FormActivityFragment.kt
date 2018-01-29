package com.overswell.ph.formmanager

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_form_element.view.*
import kotlinx.android.synthetic.main.item_form_select.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class FormActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return ItemRowGenerator(inflater, container)
                .row("test1", "Name")
                .row("test2", "Phone Number")
                .rowSelect(0, "Country", arrayOf("Australia","Kenya","Vietnam"))
                .row("test4")
                .row("test5")
                .build()

    }

}


class ItemRowGenerator(val inflater: LayoutInflater, container: ViewGroup?) {

    private val viewGroupRoot: ViewGroup

    init {
        viewGroupRoot = inflater.inflate(R.layout.fragment_form, container, false) as ViewGroup
    }

    fun row(initialValue: String, hint:String=""): ItemRowGenerator {
        val view = inflater.inflate(R.layout.item_form_element, viewGroupRoot, false)
        with(view.editText.text) {
            clear()
            insert(0, initialValue)
        }
        view.inputLayout.hint = hint
        viewGroupRoot.addView(view)
        return this
    }

    fun build(): View? {
        return viewGroupRoot
    }

    fun rowSelect(selectionIndex: Int, hint: String, strings: Array<String>): ItemRowGenerator {
        val view = inflater.inflate(R.layout.item_form_select, viewGroupRoot, false)
        val arrayAdapter = ArrayAdapter(view.context, R.layout.item_form_select_value, R.id.textViewSelectValue, strings)
        view.spinner.adapter = arrayAdapter
        view.spinner.setSelection(selectionIndex)
        viewGroupRoot.addView(view)
        return this
    }
}