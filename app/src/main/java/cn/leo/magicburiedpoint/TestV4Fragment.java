package cn.leo.magicburiedpoint;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author : Jarry Leo
 * @date : 2018/9/4 11:44
 */
public class TestV4Fragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_layout, container, false);
        Button button = inflate.findViewById(R.id.fragmentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "fragment_button", Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !isHidden()) {
            onVisibilityChange(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint() && !isHidden()) {
            onVisibilityChange(false);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (getUserVisibleHint()) {
            onVisibilityChange(!hidden);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onVisibilityChange(isVisibleToUser);
    }


    public void onVisibilityChange(boolean isVisible) {
        System.out.println(isVisible);
    }
}
