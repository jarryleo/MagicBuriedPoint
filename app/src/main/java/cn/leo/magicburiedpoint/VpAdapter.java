package cn.leo.magicburiedpoint;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @author : Jarry Leo
 * @date : 2018/11/26 14:03
 */
public class VpAdapter extends FragmentStatePagerAdapter {
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TestV4Fragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
