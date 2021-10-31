package com.ashish.githubissueslist.base

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.ashish.githubissueslist.R

abstract class BaseActivity<B : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    lateinit var binding: B
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onObserve()
        /**
         * To Prevent from tap jacking
         */
        binding.root.filterTouchesWhenObscured = true
    }

    abstract fun onObserve()

    abstract fun getViewBinding(): B

    private fun showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.dialogBuilder()
        val dialog = builder.create()
        dialog.show()
    }

    fun showErrorDialog(message: String) {
        showAlertDialog {
            setTitle(R.string.app_name)
            setMessage(message)
            positiveButton(getString(R.string.okay_text)) {
                finish()
            }
            negativeButton {}
        }
    }

    private fun AlertDialog.Builder.positiveButton(
        text: String = getString(R.string.okay_text),
        handleClick: (which: Int) -> Unit = {}
    ) {
        this.setPositiveButton(text) { _, which -> handleClick(which) }
    }

    private fun AlertDialog.Builder.negativeButton(
        text: String = getString(R.string.cancel_text),
        handleClick: (which: Int) -> Unit = {}
    ) {
        this.setNegativeButton(text) { _, which -> handleClick(which) }
    }

    fun showToast(message: String) {
        Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.INVISIBLE
    }

    fun View.remove() {
        this.visibility = View.GONE
    }

    fun String?.isTextValid(): Boolean =
        (this == null) || this.trim().isEmpty()

    fun String?.isMobileNumberValid(): Boolean =
        (this == null) || this.trim().isBlank() || this.length != 10

    fun String.isValidEmail(): Boolean =
        this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
