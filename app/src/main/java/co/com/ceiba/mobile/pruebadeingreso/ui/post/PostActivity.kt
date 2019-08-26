package co.com.ceiba.mobile.pruebadeingreso.ui.post

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.PostAdater
import co.com.ceiba.mobile.pruebadeingreso.util.LifeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class PostActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostBinding
    private lateinit var progressDialog: ProgressDialog
    private val user : User by lazy { intent.getParcelableExtra<User>(EXTRA_USER) }
    private val adapter : PostAdater by lazy { PostAdater() }
    private val linearLayoutManager = LinearLayoutManager(this)
    private val dis : LifeDisposable = LifeDisposable(this)

    private val vm: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
        title = getString(R.string.app_name)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.recyclerViewPostsResults.adapter = adapter
        binding.recyclerViewPostsResults.layoutManager = linearLayoutManager
        binding.user = user
        progressDialog = indeterminateProgressDialog(getString(R.string.generic_message_progress))
    }

    override fun onStart() {
        super.onStart()
        progressDialog.show()
    }

    override fun onResume() {
        super.onResume()

        dis add vm.getUserPostRemote(user.id)
                .subscribeBy(
                        onNext = { postList ->
                            adapter.posts = postList
                            progressDialog.hide()
                        },
                        onError = { toast(getString(R.string.generic_error)) }
                )

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_USER = "user"
    }
}
