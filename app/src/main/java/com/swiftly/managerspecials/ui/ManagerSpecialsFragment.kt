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
 * Then actual fragment for Manager Specials. Displays specials in a recycler view that is loaded on screen load
 */
class ManagerSpecialsFragment : Fragment() {

    @Inject
    lateinit var managerSpecialsViewModel: ManagerSpecialsViewModel



    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).managerSpecialsComponent.inject(this)
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