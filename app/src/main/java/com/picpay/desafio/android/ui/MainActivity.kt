package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.adapters.userListAdapter.UserListAdapter
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val usersViewModel: UsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        val adapter = UserListAdapter()

        mBinding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
            userListProgressBar.visibility = View.VISIBLE
        }

        setUserViewModelObservers(mBinding, adapter)

        setContentView(mBinding.root)
    }

    private fun setUserViewModelObservers(mBinding: ActivityMainBinding, adapter: UserListAdapter) {
        usersViewModel.users.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                mBinding.apply {
                    mBinding.userListProgressBar.visibility = View.GONE
                    adapter.users = it
                }
            }
        })

        usersViewModel.error.observe(this, Observer {
            if (it != null) {
                usersViewModel.errorShown()
                val message = getString(it)

                mBinding.apply {
                    userListProgressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }

                Toast.makeText(
                    this@MainActivity,
                    message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
