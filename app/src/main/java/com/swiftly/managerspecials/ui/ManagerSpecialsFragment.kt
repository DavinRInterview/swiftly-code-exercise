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
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.di.ManagerSpecialsComponentProvider
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.adapter.ManagerSpecialsAdapter
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import com.swiftly.managerspecials.viewmodel.ManagerSpecialsViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_manager_specials.*
import javax.inject.Inject

/**
 * The actual fragment for Manager Specials. Displays specials in a recycler view that is loaded on screen load
 */
class ManagerSpecialsFragment : Fragment() {

    @Inject
    lateinit var managerSpecialsViewModel: ManagerSpecialsViewModel



    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as ManagerSpecialsComponentProvider).getManagerSpecialsComponent().inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manager_specials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        managerSpecialsViewModel.getManagerSpecials()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                val newAdapter = ManagerSpecialsAdapter(it)
                newAdapter.setHasStableIds(true)
                manager_specials_progress_bar.apply {
                    visibility = View.GONE
                }
                manager_specials_recycler_view.apply {
                    visibility = View.VISIBLE
                    adapter = newAdapter
                }
            }, {
                manager_specials_progress_bar.apply {
                    visibility = View.GONE
                }
                Toast.makeText(activity, this.getString(R.string.service_call_failure_message), Toast.LENGTH_LONG)
            })
    }
}