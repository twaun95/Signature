package com.twaun95.signature.presentation.utils.dialog

import android.widget.SeekBar
import androidx.fragment.app.FragmentManager
import com.twaun95.signature.R
import com.twaun95.signature.databinding.FragmentDialogEraserBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener


class EraserDialog(
    private val currentWidth: Float,
    private var listener: WidthPickerListener
) : BaseDialog<FragmentDialogEraserBinding>(R.layout.fragment_dialog_eraser){

    private var penWidth = currentWidth

    fun interface WidthPickerListener {
        fun setWidth(width: Float)
    }

    override fun initView() {
        super.initView()

        binding.seekBar.progress = currentWidth.toInt()
        binding.tvWidth.text = getString(R.string.dialog_pen_width, currentWidth.toInt())
        binding.preViewEraser.initialize(currentWidth)
    }

    override fun setEvent() {
        super.setEvent()

        binding.seekBar.apply {
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    binding.tvWidth.text = getString(R.string.dialog_pen_width, progress)
                    binding.preViewEraser.onWidthChanged(progress)
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

        private const val TAG = "EraserWidthPickerDialog"

        fun show(
            fragmentManager: FragmentManager,
            currentWidth: Float,
            listener: WidthPickerListener
        ) {
            return EraserDialog(currentWidth, listener)
                .show(fragmentManager, TAG)
        }
    }
}