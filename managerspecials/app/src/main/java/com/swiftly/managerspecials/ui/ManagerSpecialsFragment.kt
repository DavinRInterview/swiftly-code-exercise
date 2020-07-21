package com.swiftly.managerspecials.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.swiftly.managerspecials.R
import com.swiftly.managerspecials.service.model.ManagerSpecialsItem
import com.swiftly.managerspecials.ui.adapter.ManagerSpecialsAdapter
import com.swiftly.managerspecials.ui.model.ManagerSpecialsRowItem
import com.swiftly.managerspecials.viewmodel.ManagerSpecialsViewModel
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

        /*
        {
      "display_name": "Noodle Dish with Roasted Black Bean Sauce",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 16
    },
    {
      "display_name": "Onion Flavored Rings",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 8
    },
    {
      "display_name": "Kikkoman Less Sodium Soy Sauce",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 8
    },
         */
        val testList = listOf(
            ManagerSpecialsRowItem(16, listOf(
                ManagerSpecialsItem(
                    "Noodle Dish with Roasted Black Bean Sauce",
                    "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
                    "2.00",
                    "1.00",
                    8,
                    16
                ))),
            ManagerSpecialsRowItem(16, listOf(
                ManagerSpecialsItem(
                    "Onion Flavored Rings",
                    "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png",
                    "2.00",
                    "1.00",
                    8,
                    8
                ),
                ManagerSpecialsItem(
                    "Kikkoman Less Sodium Soy Sauce",
                    "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/K.png",
                    "2.00",
                    "1.00",
                    8,
                    8
                )))
        )


        manager_specials_recycler_view.apply {
            adapter = ManagerSpecialsAdapter(testList)
        }
    }
}