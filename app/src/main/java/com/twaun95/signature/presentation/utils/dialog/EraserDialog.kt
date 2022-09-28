package com.twaun95.signature.presentation.utils.dialog

import com.twaun95.signature.R
import com.twaun95.signature.databinding.FragmentDialogEraserBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener
import com.twaun95.signature.presentation.extensions.visible
import com.twaun95.signature.presentation.model.DialogBody

class EraserDialog(
    private val buttonType: ButtonType,
    private val content: DialogBody,
    private var onCancelListener : (()->Unit)? = null,
    private var onConfirmListener : (()->Unit)? = null
) : BaseDialog<FragmentDialogEraserBinding>(R.layout.fragment_dialog_eraser){
    override fun initView() {
        super.initView()

        when(buttonType) {
            ButtonType.ONE -> {
                binding.layoutBottomTypeOne.visible()
            }
            ButtonType.TWO -> {
                binding.layoutBottomTypeTwo.visible()
            }
        }

        content.run {
            binding.tvTitle.text = title
            binding.tvMessage.text = message ?: ""
        }
    }

    override fun setEvent() {
        super.setEvent()

        binding.btnCancelTypeTwo.setOnSingleClickListener {
            onCancelListener?.invoke()
            dismiss()
        }
        binding.btnCompleteTypeTwo.setOnSingleClickListener {
            onConfirmListener?.invoke()
            dismiss()
        }
        binding.btnCompleteTypeOne.setOnSingleClickListener {
            onConfirmListener?.invoke()
            dismiss()
        }
    }

}