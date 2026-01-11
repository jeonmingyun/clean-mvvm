package com.min.cleanmvvm.presentation.main

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.min.cleanmvvm.R
import com.min.cleanmvvm.databinding.ActivityMainBinding
import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.presentation.base.BaseActivity
import com.min.cleanmvvm.presentation.main.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(
            onUpdateClick = { user -> showEditDialog(user) },
            onDeleteClick = { user -> viewModel.deleteUser(user) }
        )
        binding.recyclerView.adapter = userAdapter
    }

    private fun setupListeners() {
        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val age = binding.editTextAge.text.toString()
            viewModel.addUser(name, age)
            binding.editTextName.text.clear()
            binding.editTextAge.text.clear()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            // UI 상태 관찰
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerView.isVisible = false
                        }

                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerView.isVisible = true
                            userAdapter.submitList(uiState.users)
                        }

                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@MainActivity, uiState.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            // 이벤트 관찰 (Toast)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        is UiEvent.ShowToast -> {
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun showEditDialog(user: User) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_user, null)
        val editName = dialogView.findViewById<EditText>(R.id.editText_edit_name)
        val editAge = dialogView.findViewById<EditText>(R.id.editText_edit_age)

        editName.setText(user.name)
        editAge.setText(user.age.toString())

        AlertDialog.Builder(this)
            .setTitle("사용자 정보 수정")
            .setView(dialogView)
            .setPositiveButton("수정") { _, _ ->
                viewModel.updateUser(user, editName.text.toString(), editAge.text.toString())
            }
            .setNegativeButton("취소", null)
            .show()
    }
}
