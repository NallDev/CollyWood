package com.nalldev.collywood.presentation.logres

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import com.nalldev.collywood.BaseActivity
import com.nalldev.collywood.presentation.movies.MainActivity
import com.nalldev.collywood.R
import com.nalldev.collywood.databinding.ActivityLoginRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginRegisterActivity : BaseActivity<ActivityLoginRegisterBinding>() {
    private val viewModel: LogResViewModel by viewModel()

    override fun getViewBinding() = ActivityLoginRegisterBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
        initEventListener()
    }

    private fun initObserver() {
        viewModel.apply {
            isUserLogin.observe(this@LoginRegisterActivity) { isLogin ->
                if (isLogin) {
                    Intent(this@LoginRegisterActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }

            loginResult.observe(this@LoginRegisterActivity) {user ->
                if (user != null) {
                    showToast("Login Berhasil")
                    setUserLogin()
                    Intent(this@LoginRegisterActivity, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                } else {
                    showToast("Login gagal")
                }
            }

            registrationResult.observe(this@LoginRegisterActivity) { isCreated ->
                if (isCreated) {
                    showToast("Register Berhasil!")
                    loginScreen()
                } else {
                    showToast("Register Gagal!")
                }
            }
        }
    }

    private fun initEventListener() {
        binding.apply {
            tvSecondStatement.setOnClickListener {
                if (tvSecondStatement.text == getString(R.string.login_now)) {
                    loginScreen()
                } else {
                    registerScreen()
                }
            }

            btnLogRes.setOnClickListener {
                if (btnLogRes.text == getString(R.string.login_now)) {
                    doLogin()
                } else {
                    doRegister()
                }
            }

            onBackPressedDispatcher.addCallback(this@LoginRegisterActivity, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (tvSecondStatement.text == getString(R.string.login_now)){
                        loginScreen()
                        return
                    }
                    finish()
                }
            })
        }
    }

    private fun doRegister() {
        binding.apply {
            viewModel.register(
                etEmail.text.toString(),
                etPhone.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    private fun doLogin() {
        binding.apply {
            viewModel.login(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    private fun loginScreen() {
        binding.apply {
            AdditiveAnimator.animate(tilPhone).fadeVisibility(View.GONE).start()
            tvFirstStatement.text = getString(R.string.don_t_have_an_account)
            tvSecondStatement.text = getString(R.string.create_now)
            tvTitle.text = getString(R.string.hello_welcome)
            tvSubtitle.text = getString(R.string.subtitle_log)
            btnLogRes.text = getString(R.string.login_now)
            clearInput()
        }
    }

    private fun registerScreen() {
        binding.apply {
            AdditiveAnimator.animate(tilPhone).fadeVisibility(View.VISIBLE).start()
            tvFirstStatement.text = getString(R.string.already_have_an_account)
            tvSecondStatement.text = getString(R.string.login_now)
            tvTitle.text = getString(R.string.create_account_title)
            tvSubtitle.text = getString(R.string.subtitle_reg)
            btnLogRes.text = getString(R.string.create_account)
            clearInput()
        }
    }

    private fun clearInput() {
        binding.apply {
            etEmail.text?.clear()
            etPassword.text?.clear()
            etPhone.text?.clear()
        }
    }
}