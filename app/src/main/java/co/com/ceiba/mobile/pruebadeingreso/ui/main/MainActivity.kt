package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.ui.post.PostActivity
import co.com.ceiba.mobile.pruebadeingreso.ui.post.PostActivity.Companion.EXTRA_USER
import co.com.ceiba.mobile.pruebadeingreso.util.LifeDisposable
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Boolean.TRUE


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private val adapter : UserAdapter by lazy { UserAdapter() }
    private val linearLayoutManager = LinearLayoutManager(this)
    private val dis = LifeDisposable(this)
    private val getUserListRemoteSubject: PublishSubject<Unit> = PublishSubject.create()
    private var allUsers = mutableListOf<User>()

    val vm : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerViewSearchResults.adapter = adapter
        binding.recyclerViewSearchResults.layoutManager = linearLayoutManager
        progressDialog = indeterminateProgressDialog(getString(R.string.generic_message_progress))
    }

    override fun onStart() {
        super.onStart()
        progressDialog.show()
    }

    override fun onResume() {
        super.onResume()

        dis add vm.getUserListLocal()
                .subscribeBy(
                        onNext = { userList ->
                            if (userList.isEmpty()) getUserListRemoteSubject.onNext(Unit)
                            else {
                                allUsers = userList.toMutableList()
                                adapter.users = userList
                                binding.empty = userList.isEmpty()
                                progressDialog.hide()
                            }
                        },
                        onError = { toast(getString(R.string.generic_error)) }
                )

        dis add getUserListRemoteSubject
                .flatMap { vm.getUserListRemote() }
                .flatMap { userList -> vm.insertUserListLocal(userList) }
                .subscribeBy(
                        onNext = {
                            allUsers = it.toMutableList()
                            adapter.users = it
                            binding.empty = it.isEmpty()
                            progressDialog.hide()
                        },
                        onError = { toast(getString(R.string.generic_error)) }
                )

        dis add binding.editTextSearch.textChanges()
                .subscribeBy(
                        onNext = {parameter -> parameter.toString()
                            val filteredUsers = allUsers.filter { usr -> usr.name.contains(parameter, TRUE) }
                            adapter.users = filteredUsers
                            binding.empty = filteredUsers.isEmpty()
                        },
                        onError = { toast(getString(R.string.generic_error)) }
                )

        dis add adapter.clickUserSubject
                .subscribeBy(
                        onNext = { user -> startActivity<PostActivity>(EXTRA_USER to user) },
                        onError = { toast(getString(R.string.generic_error)) }
                )
    }


}