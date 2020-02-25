package co.id.putra.webviewgithub.welcomepackage1243;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.id.putra.webviewgithub.MainActivity;
import co.id.putra.webviewgithub.R;
import co.id.putra.webviewgithub.herohelper.PreferencesManager;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotslayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnskip, btnnext;
    private PreferencesManager prefManager;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        prefManager = new PreferencesManager(this);
        if(!prefManager.isFirstTimeLaunch()){
            launchHomeScreen();
        }
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_welcome);
        viewPager =(ViewPager)findViewById(R.id.view_pager);
        dotslayout =(LinearLayout)findViewById(R.id.layoutDots);
        btnskip =(Button)findViewById(R.id.btn_skip);
        btnnext =(Button)findViewById(R.id.btn_next);


        layouts = new int[]{
                R.layout.intro1,
                R.layout.intro2,
                R.layout.intro3,
        };

        addButtomDots(0);
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if(current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else{
                    launchHomeScreen();
                }
            }
        });

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int posisition) {
            addButtomDots(posisition);
            if(posisition == layouts.length -1) {
                btnnext.setText(getString(R.string.start));
                btnskip.setVisibility(View.GONE);
            }else {
                btnnext.setText(getString(R.string.next));
                btnskip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private void addButtomDots(int currentpage) {
        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorInActive = getResources().getIntArray(R.array.array_dot_inactive);
        dotslayout.removeAllViews();
        for (int i =0 ; i< dots.length;i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInActive[currentpage]);
            dotslayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentpage].setTextColor(colorActive[currentpage]);

    }

    private void launchHomeScreen() {
        prefManager.SetIsFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    private int getItem(int i){
        return  viewPager.getCurrentItem() + 1 ;
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position],container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj ;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view =(View)object;
            container.removeView(view);
        }
    }
}
