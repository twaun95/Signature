package com.twaun95.signature.presentation.utils.dialog

import androidx.fragment.app.FragmentManager
import com.twaun95.signature.R
import com.twaun95.signature.databinding.FragmentDialogPenWidthBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener

class WidthPickerDialog(
    private val currentWidth: Float,
    private var listener: WidthPickerListener
) : BaseDialog<FragmentDialogPenWidthBinding>(R.layout.fragment_dialog_pen_width){

    fun interface WidthPickerListener {
        fun setWidth(width: Float)
    }

    override fun initView() {
        super.initView()

        binding.tvMessage.text = currentWidth.toString()
    }

    override fun setEvent() {
        super.setEvent()

        binding.btnCancel.setOnSingleClickListener {
            dismiss()
        }
        binding.btnComplete.setOnSingleClickListener {
            listener.setWidth(10f)
            dismiss()
        }
    }

    companion object {

        private const val TAG = "WidthPickerDialog"

        fun show(
            fragmentManager: FragmentManager,
            currentWidth: Float,
            listener: WidthPickerListener
        ) {
            return WidthPickerDialog(currentWidth, listener)
                .show(fragmentManager, TAG)
        }
    }
}