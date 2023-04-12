package com.example.kitchendiaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.example.kitchendiaries.entities.MealCategory
import com.example.kitchendiaries.interfaces.GetDataInteface
import com.example.kitchendiaries.retrofitclient.RFClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val backgroudImage : ImageView = findViewById(R.id.splash_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_slide)
        backgroudImage.startAnimation(sideAnimation)


        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }

    fun getMealCategories(){
        val service = RFClientInstance.retrofitInstance.create(GetDataInteface::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : Callback<List<MealCategory>> {
            override fun onResponse(
                call: Call<List<MealCategory>>,
                response: Response<List<MealCategory>>
            ) {
                insertDataIntoDB(response.body())
            }

            override fun onFailure(call: Call<List<MealCategory>>, t: Throwable) {
                Toast.makeText(this@SplashScreenActivity, "Something went wrong. Please try again later.", Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun insertDataIntoDB(category: List<MealCategory>?){

    }
}