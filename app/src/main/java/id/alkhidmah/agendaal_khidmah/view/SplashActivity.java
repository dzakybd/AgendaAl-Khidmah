package id.alkhidmah.agendaal_khidmah.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.pixplicity.easyprefs.library.Prefs;

import id.alkhidmah.agendaal_khidmah.R;

public class SplashActivity extends AppCompatActivity {

    LinearLayout groupsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        groupsplash = findViewById(R.id.groupsplash);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        createanimation();
    }

    private void createanimation() {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(groupsplash, "alpha", 0f, 1f);
        fadeIn.setDuration(1000);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, PermissionActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 1000);
            }
        });

        mAnimationSet.start();
    }
}
