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
import com.swiftly.managerspecials.di.ManagerSpecialsComponentProvider
import com.swiftly.managerspecials.viewmodel.ManagerSpecialsViewModel
import com.swiftly.managerspecials.databinding.FragmentManagerSpecialsBinding
import javax.inject.Inject

/**
 * The actual fragment for Manager Specials. Displays specials in a recycler view that is loaded on screen load
 */
class ManagerSpecialsFragment : Fragment() {

    @Inject
    lateinit var managerSpecialsViewModel: ManagerSpecialsViewModel

    private var binding: FragmentManagerSpecialsBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as ManagerSpecialsComponentProvider).getManagerSpecialsComponent().inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManagerSpecialsBinding.inflate(inflater, container, false)
        binding?.apply {
            vm = managerSpecialsViewModel
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managerSpecialsViewModel.updateSpecialsData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}