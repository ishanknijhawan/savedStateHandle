package com.example.savedstatehandle

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

class FragmentB : Fragment(R.layout.fragment_b) {

    private lateinit var calculateButton: MaterialButton
    private lateinit var etFirst: EditText
    private lateinit var etSecond: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculateButton = view.findViewById(R.id.btn_calculate)
        etFirst = view.findViewById(R.id.et_first)
        etSecond = view.findViewById(R.id.et_second)
        val type = arguments?.getString(MainActivity.TYPE)

        calculateButton.setOnClickListener {
            if (!etFirst.text.isNullOrBlank() and !etSecond.text.isNullOrBlank()) {
                val resultAdd = etFirst.text.toString().toInt() + etSecond.text.toString().toInt()
                val resultSubtract =
                    etFirst.text.toString().toInt() - etSecond.text.toString().toInt()
                when (type) {
                    Calculation.ADD.value ->
                        findNavController().apply {
                            previousBackStackEntry
                                ?.savedStateHandle?.set(MainActivity.RESULT, resultAdd)
                        }.navigateUp()
                    else -> findNavController().apply {
                        previousBackStackEntry
                            ?.savedStateHandle?.set(MainActivity.RESULT, resultSubtract)
                    }.navigateUp()
                }
            }
        }
    }
}