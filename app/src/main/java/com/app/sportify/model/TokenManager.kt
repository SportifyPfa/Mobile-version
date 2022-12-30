package com.app.sportify.model

import android.content.Context
import com.app.sportify.model.utils.UserLoginResponse
import com.app.sportify.utils.ConstUtil.ACCESS_TOKEN
import com.app.sportify.utils.ConstUtil.PREFS_FILE
import com.app.sportify.utils.ConstUtil.REFRESH_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)


    fun saveToken(userLoginResponse: UserLoginResponse) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, userLoginResponse.access_token)
        editor.putString(REFRESH_TOKEN, userLoginResponse.refresh_token)
        editor.apply()
    }

    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN, null)
    fun getRefreshToken(): String? = prefs.getString(REFRESH_TOKEN, null)
}