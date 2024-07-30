package com.example.menuoptions

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {

    private lateinit var listener: CustomDialogListener

    interface CustomDialogListener {
        fun onDialogPositiveClick(percentage: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as CustomDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement CustomDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = androidx.appcompat.app.AlertDialog.Builder(it)
            val inflater: LayoutInflater = requireActivity().layoutInflater
            val view: View = inflater.inflate(R.layout.fragment_custom_dialog, null)

            val editTextPercentage = view.findViewById<EditText>(R.id.editTextPercentage)
            val buttonOk = view.findViewById<Button>(R.id.buttonOk)

            buttonOk.setOnClickListener {
                val percentage = editTextPercentage.text.toString().toInt()
                listener.onDialogPositiveClick(percentage)
                dismiss()
            }

            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
