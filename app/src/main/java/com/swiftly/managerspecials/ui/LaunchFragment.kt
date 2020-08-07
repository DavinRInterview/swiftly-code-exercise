/*
MIT License

Copyright (c) 2020 DavinRInterview

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.swiftly.managerspecials.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.navigation.fragment.findNavController
import com.swiftly.managerspecials.R

/**
 * The launching fragment for the test application
 */
class LaunchFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.manager_specials_fragment_button).setOnClickListener {
            findNavController().navigate(R.id.action_LaunchFragment_to_ManagerSpecialsFragment)
        }

        view.findViewById<Switch>(R.id.local_data_source_switch).setOnCheckedChangeListener { _, isChecked ->
            val sharedPrefs = activity!!.getSharedPreferences(getString(R.string.testing_prefs), Context.MODE_PRIVATE)
            with (sharedPrefs.edit()) {
                this.putBoolean(getString(R.string.local_data), isChecked)
                commit()
            }
        }

        view.findViewById<Switch>(R.id.service_failure_switch).setOnCheckedChangeListener { _, isChecked ->
            val sharedPrefs = activity!!.getSharedPreferences(getString(R.string.testing_prefs), Context.MODE_PRIVATE)
            with (sharedPrefs.edit()) {
                this.putBoolean(getString(R.string.service_failure), isChecked)
                commit()
            }
        }
    }
}