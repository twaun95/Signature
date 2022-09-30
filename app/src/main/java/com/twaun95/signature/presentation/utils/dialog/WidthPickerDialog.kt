package com.twaun95.signature.presentation.utils.dialog

import android.widget.SeekBar
import androidx.fragment.app.FragmentManager
import com.twaun95.signature.R
import com.twaun95.signature.databinding.FragmentDialogPenWidthBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener

class WidthPickerDialog(
    private val currentWidth: Float,
    private val currentColor: Int,
    private var listener: WidthPickerListener
) : BaseDialog<FragmentDialogPenWidthBinding>(R.layout.fragment_dialog_pen_width){


    private var penWidth = currentWidth

    fun interface WidthPickerListener {
        fun setWidth(width: Float)
    }

    override fun initView() {
        super.initView()

        binding.seekBar.progress = currentWidth.toInt()
        binding.preViewPen.initialize(currentWidth, currentColor)
        binding.tvWidth.text = getString(R.string.dialog_pen_width, currentWidth.toInt())
    }

    override fun setEvent() {
        super.setEvent()

        binding.seekBar.apply {
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    binding.tvWidth.text = getString(R.string.dialog_pen_width, progress)
                    binding.preViewPen.onWidthChanged(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    penWidth =  seekBar.progress.toFloat()
                }
            })
        }


        binding.btnCancel.setOnSingleClickListener {
            dismiss()
        }
        binding.btnComplete.setOnSingleClickListener {
            listener.setWidth(penWidth)
            dismiss()
        }
    }

    companion object {

        private const val TAG = "WidthPickerDialog"

        fun show(
            fragmentManager: FragmentManager,
            currentWidth: Float,
            currentColor: Int,
            listener: WidthPickerListener
        ) {
            return WidthPickerDialog(currentWidth, currentColor, listener)
                .show(fragmentManager, TAG)
        }
    }
}