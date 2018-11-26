package cn.leo.magicburiedpoint;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Leo
 */
public class MainActivity extends BaseActivity {

    private TextView mTextView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.vp);
        mViewPager.setAdapter(new VpAdapter(getSupportFragmentManager()));

        mTextView = findViewById(R.id.tvTest1);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
//        testFragment();
//        testFragment1();
    }

    private void testFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new TestV4Fragment());
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void testFragment1() {
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new TestFragment1());
        fragmentTransaction.commitAllowingStateLoss();
    }


}
