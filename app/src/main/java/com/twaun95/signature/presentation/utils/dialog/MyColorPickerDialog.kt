package com.twaun95.signature.presentation.utils.dialog

import android.widget.SeekBar
import androidx.fragment.app.FragmentManager
import com.twaun95.signature.R
import com.twaun95.signature.databinding.FragmentDialogColorPickerBinding
import com.twaun95.signature.databinding.FragmentDialogPenWidthBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener

class MyColorPickerDialog(
    private val currentWidth: Float,
    private val currentColor: Int,
    private var onDismissListener: (()->Unit)? = null
) : BaseDialog<FragmentDialogColorPickerBinding>(R.layout.fragment_dialog_color_picker){


    private var penWidth = currentWidth



    override fun initView() {
        super.initView()

        binding.colorPickerView.apply {

        }

//        binding.seekBar.progress = currentWidth.toInt()
//        binding.preViewPen.initialize(currentWidth, currentColor)
//        binding.tvWidth.text = getString(R.string.dialog_pen_width, currentWidth.toInt())
    }

    override fun setEvent() {
        super.setEvent()

//        binding.seekBar.apply {
//            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
//                    binding.tvWidth.text = getString(R.string.dialog_pen_width, progress)
//                    binding.preViewPen.onWidthChanged(progress)
//                }
//
//                override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                }
//
//                override fun onStopTrackingTouch(seekBar: SeekBar) {
//                    penWidth =  seekBar.progress.toFloat()
//                }
//            })
//        }
//
//
//        binding.btnCancel.setOnSingleClickListener {
//            onDismissListener?.invoke()
//            dismiss()
//        }
//        binding.btnComplete.setOnSingleClickListener {
//            onDismissListener?.invoke()
//            dismiss()
//        }
    }

    companion object {

        private const val TAG = "WidthPickerDialog"

        fun show(
            fragmentManager: FragmentManager,
            currentWidth: Float,
            currentColor: Int,
            onDismissListener: (()->Unit)? = null
        ) {
            return MyColorPickerDialog(currentWidth, currentColor, onDismissListener)
                .show(fragmentManager, TAG)
        }
    }
}