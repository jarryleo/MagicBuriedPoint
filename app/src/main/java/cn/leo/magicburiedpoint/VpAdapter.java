package cn.leo.magicburiedpoint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author : Jarry Leo
 * @date : 2018/11/26 14:03
 */
public class VpAdapter extends FragmentPagerAdapter {
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TestFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
