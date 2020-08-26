package cn.leo.buried.point;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Leo on 2018/5/2.
 */
@Aspect
public class BuriedPointAspect {

    private static final String POINTCUT_ON_CLICK =
            "execution(* onClick(..))";
    private static final String POINTCUT_BUTTER_KNIFE =
            "execution(@butterknife.OnClick * *(..))";
    private static final String POINT_ACTIVITY_ON_SHOW =
            "execution(* android.app.Activity+.onResume(..))";
    private static final String POINT_FRAGMENT_ON_SHOW =
            "execution(* androidx.fragment.app.Fragment+.onResume(..))";
    private static final String POINT_ACTIVITY_ON_HIDE =
            "execution(* android.app.Activity+.onPause(..))";
    private static final String POINT_FRAGMENT_ON_HIDE =
            "execution(* androidx.fragment.app.Fragment+.onPause(..))";
    private static final String POINT_FRAGMENT_SET_USER_VISIBLE_HINT =
            "execution(* androidx.fragment.app.Fragment+.setUserVisibleHint(..))";
    private static final String POINT_FRAGMENT_ON_HIDDEN_CHANGED =
            "execution(* androidx.fragment.app.Fragment+.onHiddenChanged(..))";


    @Pointcut(POINT_ACTIVITY_ON_SHOW)
    public void activityOnShowPointcut() {

    }

    @Pointcut(POINT_FRAGMENT_ON_SHOW)
    public void fragmentOnShowPointcut() {

    }


    @Pointcut(POINT_ACTIVITY_ON_HIDE)
    public void activityOnHidePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_ON_HIDE)
    public void fragmentOnHidePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_SET_USER_VISIBLE_HINT)
    public void fragmentSetUserVisibleHint() {

    }

    @Pointcut(POINT_FRAGMENT_ON_HIDDEN_CHANGED)
    public void fragmentOnHiddenChanged() {

    }

    @Pointcut(POINTCUT_ON_CLICK)
    public void methodPointcut() {

    }


    @Pointcut(POINTCUT_BUTTER_KNIFE)
    public void butterKnifePointcut() {

    }

    @After("methodPointcut() || butterKnifePointcut()")
    public void aroundJoinClickPoint(final JoinPoint joinPoint) throws Throwable {
        try {
            Object target = joinPoint.getTarget();
            String className = "";
            if (target != null) {
                className = target.getClass().getName();
            }
            //点击事件
            Object[] args = joinPoint.getArgs();
            if (args.length >= 1 && args[0] instanceof View) {
                View view = (View) args[0];
                int id = view.getId();
                String entryName = view.getResources().getResourceEntryName(id);
                MagicBuriedPoint.onClick(className, entryName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("activityOnShowPointcut()")
    public void aroundJoinActivityOpenPoint(final JoinPoint joinPoint) throws Throwable {
        try {
            Activity target = (Activity) joinPoint.getTarget();
            String className = target.getClass().getName();
            MagicBuriedPoint.onPageOpen(className, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("activityOnHidePointcut()")
    public void aroundJoinActivityClosePoint(final JoinPoint joinPoint) throws Throwable {
        try {
            Activity target = (Activity) joinPoint.getTarget();
            String className = target.getClass().getName();
            MagicBuriedPoint.onPageClose(className, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @After("fragmentOnShowPointcut()")
    public void aroundJoinFragmentOpenPoint(final JoinPoint joinPoint) throws Throwable {
        try {
            Fragment fragment = (Fragment) joinPoint.getTarget();
            String className = fragment.getClass().getName();
            if (fragment.getUserVisibleHint() && !fragment.isHidden()) {
                MagicBuriedPoint.onPageOpen(className, fragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @After("fragmentOnHidePointcut()")
    public void aroundJoinFragmentClosePoint(final JoinPoint joinPoint) throws Throwable {
        try {
            Fragment fragment = (Fragment) joinPoint.getTarget();
            String className = fragment.getClass().getName();
            if (fragment.getUserVisibleHint() && !fragment.isHidden()) {
                MagicBuriedPoint.onPageClose(className, fragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @After("fragmentSetUserVisibleHint()")
    public void aroundJoinSetUserVisibleHint(final JoinPoint joinPoint) throws Throwable {
        try {
            Fragment target = (Fragment) joinPoint.getTarget();
            String className = target.getClass().getName();
            Object[] args = joinPoint.getArgs();
            if (target.isResumed()) {
                boolean isVisibleToUser = (boolean) args[0];
                if (isVisibleToUser) {
                    MagicBuriedPoint.onPageOpen(className, target);
                } else {
                    MagicBuriedPoint.onPageClose(className, target);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @After("fragmentOnHiddenChanged()")
    public void aroundJoinOnHiddenChanged(final JoinPoint joinPoint) throws Throwable {
        try {
            Fragment target = (Fragment) joinPoint.getTarget();
            String className = target.getClass().getName();
            Object[] args = joinPoint.getArgs();
            boolean isHidden = (boolean) args[0];
            if (isHidden) {
                MagicBuriedPoint.onPageClose(className, target);
            } else {
                MagicBuriedPoint.onPageOpen(className, target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
