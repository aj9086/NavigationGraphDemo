package aj.navigationcomponents.utils

import aj.navigationcomponents.model.UserInfo
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class PrefStore {


    private var context: Context? = null
    private var sharedPref: SharedPreferences? = null
    private val mypreference = "pref"
    internal var prefEditor: SharedPreferences.Editor? = null

    constructor(context: Context) {
        this.context = context
        initPref()
    }

    fun initPref() {
        sharedPref = context?.getSharedPreferences(
            mypreference,
            Context.MODE_PRIVATE
        )
        prefEditor = sharedPref!!.edit()
    }

    fun setLanguage(value: String) {
        prefEditor?.putString("pref", value)?.commit()
    }

    fun getLanguage(): String? {
        return sharedPref!!.getString("pref", null)
    }


    private fun getPref(): SharedPreferences? {
        return sharedPref
    }

    fun cleanPref() {
        val settings = getPref()
        settings!!.edit().clear().apply()
    }

    fun containValue(key: String): Boolean {
        val settings = getPref()
        return settings!!.contains(key)
    }

    fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val settings = getPref()
        return settings!!.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        val settings = getPref()
        val editor = settings!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun saveString(key: String, value: String) {
        val settings = getPref()
        val editor = settings!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveBool(key: String, value: Boolean?) {
        val settings = getPref()
        val editor = settings!!.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }

    fun getString(key: String): String? {
        return getString(key, null)
    }

    fun getString(key: String, defaultVal: String?): String? {
        val settings = getPref()
        return settings!!.getString(key, defaultVal)
    }


    fun saveLong(key: String, value: Long) {
        val settings = getPref()
        val editor = settings!!.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String): Long {
        return getLong(key, 0)
    }

    fun getLong(key: String, defaultVal: Long): Long {
        val settings = getPref()
        return settings!!.getLong(key, defaultVal)
    }

    fun setInt(subscription_id: String, sbu_id: Int) {
        val settings = getPref()
        val editor = settings!!.edit()
        editor.putInt(subscription_id, sbu_id)
        editor.apply()
    }

    fun storeUserInfo(user: UserInfo) {
        val editor = sharedPref!!.edit()
        editor.putString("UserInfo", Gson().toJson(user))
        editor.apply()
    }

    fun restoreUserInfo(): UserInfo {
        val gson = Gson()
        val json = sharedPref!!.getString("UserInfo", "")
        return gson.fromJson(json, UserInfo::class.java)
    }

    /* public void storeProfileData(MyProfileData user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("profileData", new Gson().toJson(user));
        editor.apply();
    }

    public MyProfileData restoreProfileData() {
        Gson gson = new Gson();
        String json = sharedPref.getString("profileData", "");
        return gson.fromJson(json, MyProfileData.class);
    }*/


    fun getInt(key: String): Int {
        return getInt(key, 0)
    }

    fun getInt(key: String, defaultVal: Int): Int {
        val settings = getPref()
        return settings!!.getInt(key, defaultVal)
    }

    fun <T : Any> setData(value: String, datas: ArrayList<T>) {
        getPref()!!.edit().putString(value, ObjectSerializer.serialize(datas)).commit()
    }

    fun <T : Any> getData(name: String): Any? {
        return ObjectSerializer.deserialize(getPref()!!.getString(name, ObjectSerializer.serialize(ArrayList<T>())))
    }

     fun setUserInfo(datas: UserInfo) {
         getPref()!!.edit().putString("UserInfo", ObjectSerializer.serialize(datas)).commit()
     }

     fun getUserInfo(): UserInfo {
         return ObjectSerializer.deserialize(getPref()!!.getString("UserInfo", null)) as UserInfo
     }

}