package com.example.loginmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loginmvvm.viewModel.RegisterViewModel
import com.example.loginmvvm.repository.UserRepository
import com.example.loginmvvm.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }
    private val userRepository: UserRepository by lazy {
        UserRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAction.setOnClickListener {
            val username = binding.etUsername.text.toString()

            val userDataList = userRepository.readUserData()

            if (username.isNotBlank()) {
                viewModel.isAccountExist(username, userDataList)
            } else {
                Toast.makeText(requireContext(), "Đăng ký không thành công", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.accountExist.observe(viewLifecycleOwner) { exist ->
            if (exist) {
                Toast.makeText(requireContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show()
            } else {
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                if (username.isNotBlank() && password.isNotBlank()) {
                    userRepository.saveUserData(username, password)
                    Toast.makeText(requireContext(), "Đăng ký thành công", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Đăng ký không thành công", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
