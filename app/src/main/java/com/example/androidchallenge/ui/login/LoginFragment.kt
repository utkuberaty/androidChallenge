package com.example.androidchallenge.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidchallenge.R
import com.example.androidchallenge.base.BaseFragment
import com.example.androidchallenge.data.Auth
import com.example.androidchallenge.databinding.FragmentLoginBinding
import com.example.androidchallenge.util.setOnSafeClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})"

class LoginFragment : BaseFragment<FragmentLoginBinding>({ FragmentLoginBinding.inflate(it) }) {

    private val viewModel: LoginViewModel by viewModel()

    private val navHostController by lazy { findNavController() }

    private val username
        get() = viewBinding.userNameTextInputLayout.editText?.text.toString()
    private val password
        get() = viewBinding.passwordTextInputLayout.editText?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.apply {
            continueButton.setOnSafeClickListener {
                checkAuth { isSuccess, message ->
                    if (isSuccess) {
                        progressbarConstraintLayout.visibility = View.VISIBLE
                        saveAuthData(username, password)
                        getData()
                    } else errorMessage(message)
                }
            }
        }
    }

    private fun checkAuth(check: (Boolean, String) -> Unit) {
        if (username.count() < 2) check(false, getString(R.string.username_rule))
        else {
            if (validate(password)) check(true, "")
            else check(false, getString(R.string.password_rule))
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getData().collect {
                viewModel.saveData(it)
                navHostController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }
    }

    private fun saveAuthData(username: String, password: String) {
        viewModel.saveLoginData(Auth(username, password))
    }

    private fun validate(password: String): Boolean {
        return password.matches(PASSWORD_PATTERN.toRegex())
    }

}