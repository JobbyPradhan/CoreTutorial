package com.corevalue.tutorial

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.corevalue.tutorial.databinding.ActivityLoginBinding
import com.corevalue.tutorial.util.setUpKeyboardListener
import com.corevalue.tutorial.util.setUpKeyboardVisibilityListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private var isPasswordVisible = false

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.requestApplyInsets()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpKeyboardVisibilityListener(binding.scrollView,binding.btnLogin, 333)
        binding.ivEye.setOnClickListener {

            if (isPasswordVisible) {
                // Hide password
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivEye.setImageResource(R.drawable.baseline_visibility_off_24)
            } else {
                // Show password
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ivEye.setImageResource(R.drawable.eye)
            }

            // Move cursor to the end of the text
            binding.etPassword.setSelection(binding.etPassword.text.length)

            isPasswordVisible = !isPasswordVisible
        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun scrollToBottomOnFocus(
        vararg editTexts: View,
        scrollView: ScrollView,
        targetView: View,
        bottomPadding: Int = 333
    ) {
        for (editText in editTexts) {
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    scrollView.setPadding(0, 0, 0, bottomPadding)
                    scrollView.post {
                        scrollView.smoothScrollTo(0, targetView.bottom)
                    }
                }
            }
        }
    }


}