package com.fls.self_care_assistant.fragments.auth


import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.LoginViewPagerAdapter
import com.fls.self_care_assistant.main.MainActivity
import com.fls.self_care_assistant.repositories.TokenRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val tokenRepository = TokenRepository.instance
        tokenRepository.setupSharedPrefs(getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        ))
//        if (tokenRepository.getToken() != null && !tokenRepository.isExpired) {
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        initTabs()

        //TODO temp solution
        if (VK.isLoggedIn()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initTabs() {
        supportActionBar?.hide()

        val viewPager: ViewPager2 = findViewById(R.id.activity_auth__view_pager)
        val adapter = LoginViewPagerAdapter(this)
        viewPager.adapter = adapter
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val tabs: TabLayout = findViewById(R.id.activity_auth__tabs)

        val titles = listOf("Sign In", "Sign Up")
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //TODO fix request codes
        if (requestCode == LoginFragment.RC_SIGN_IN) {
            println("_------------------------------------------------")
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                // Signed in successfully, show authenticated UI.
                val intent = Intent(applicationContext, MainActivity::class.java)

                intent.putExtra("account", account)
                startActivity(intent)
                finish()

            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            }

        }
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                val intent = Intent(applicationContext, MainActivity::class.java)

                intent.putExtra("token", token.accessToken)
                startActivity(intent)
                finish()
            }

            override fun onLoginFailed(errorCode: Int) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Auth failed!", Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}