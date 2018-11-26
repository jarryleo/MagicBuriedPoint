package cn.leo.buried.point;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Leo on 2018/5/2.
 */
@Aspect
public class BuriedPointAspect {

    private static final String POINTCUT_ON_CLICK =
            "execution(* on*Click(..))";
    private static final String POINTCUT_BUTTER_KNIFE =
            "execution(@butterknife.OnClick * *(..))";
    private static final String POINT_ACTIVITY_ON_SHOW =
            "execution(* android.app.Activity+.onResume(..))";
    private static final String POINT_FRAGMENT_ON_SHOW =
            "execution(* android.app.Fragment+.onResume(..))";
    private static final String POINT_FRAGMENT_V4_ON_SHOW =
            "execution(* android.support.v4.app.Fragment+.onResume(..))";
    private static final String POINT_ACTIVITY_ON_HIDE =
            "execution(* android.app.Activity+.onPause(..))";
    private static final String POINT_FRAGMENT_ON_HIDE =
            "execution(* android.app.Fragment+.onPause(..))";
    private static final String POINT_FRAGMENT_V4_ON_HIDE =
            "execution(* android.support.v4.app.Fragment+.onPause(..))";
    private static final String POINT_FRAGMENT_SET_USER_VISIBLE_HINT =
            "execution(* android.app.Fragment+.setUserVisibleHint(..))";
    private static final String POINT_FRAGMENT_V4_SET_USER_VISIBLE_HINT =
            "execution(* android.support.v4.app.Fragment+.setUserVisibleHint(..))";

    @Pointcut(POINT_ACTIVITY_ON_SHOW)
    public void activityOnShowPointcut() {

    }

    @Pointcut(POINT_FRAGMENT_ON_SHOW)
    public void fragmentOnShowPointcut() {

    }

    @Pointcut(POINT_FRAGMENT_V4_ON_SHOW)
    public void fragmentV4OnShowPointcut() {

    }

    @Pointcut(POINT_ACTIVITY_ON_HIDE)
    public void activityOnHidePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_ON_HIDE)
    public void fragmentOnHidePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_V4_ON_HIDE)
    public void fragmentV4OnHidePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_SET_USER_VISIBLE_HINT)
    public void fragmentSetUserVisibleHint() {

    }

    @Pointcut(POINT_FRAGMENT_V4_SET_USER_VISIBLE_HINT)
    public void fragmentV4SetUserVisibleHint() {

    }

    @Pointcut(POINTCUT_ON_CLICK)
    public void methodPointcut() {

    }


    @Pointcut(POINTCUT_BUTTER_KNIFE)
    public void butterKnifePointcut() {

    }

    @Around("methodPointcut() || butterKnifePointcut()")
    public void aroundJoinClickPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
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
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Around("activityOnShowPointcut()")
    public void aroundJoinActivityOpenPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object target = joinPoint.getTarget();
            String className = target.getClass().getName();
            MagicBuriedPoint.onPageOpen(className);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Around("activityOnHidePointcut()")
    public void aroundJoinActivityClosePoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object target = joinPoint.getTarget();
            String className = target.getClass().getName();
            MagicBuriedPoint.onPageClose(className);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Around("fragmentOnShowPointcut()")
    public void aroundJoinFragmentOpenPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Fragment fragment = (Fragment) joinPoint.getTarget();
            String className = fragment.getClass().getName();
            if (fragment.getUserVisibleHint()) {
                MagicBuriedPoint.onPageOpen(className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Around("fragmentOnHidePointcut()")
    public void aroundJoinFragmentClosePoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Fragment fragment = (Fragment) joinPoint.getTarget();
            String className = fragment.getClass().getName();
            if (fragment.getUserVisibleHint()) {
                MagicBuriedPoint.onPageClose(className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Around("fragmentV4OnShowPointcut()")
    public void aroundJoinFragmentV4OpenPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            android.support.v4.app.Fragment fragment =
                    (android.support.v4.app.Fragment) joinPoint.getTarget();
            String className = fragment.getClass().getName();
            if (fragment.getUserVisibleHint()) {
                MagicBuriedPoint.onPageOpen(className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Around("fragmentV4OnHidePointcut()")
    public void aroundJoinFragmentV4ClosePoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            android.support.v4.app.Fragment fragment =
                    (android.support.v4.app.Fragment) joinPoint.getTarget();
            String className = fragment.getClass().getName();
            if (fragment.getUserVisibleHint()) {
                MagicBuriedPoint.onPageClose(className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Around("fragmentSetUserVisibleHint()")
    public void aroundJoinSetUserVisibleHint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Fragment target = (Fragment) joinPoint.getTarget();
            String className = target.getClass().getName();
            Object[] args = joinPoint.getArgs();
            if (target.isResumed()) {
                boolean isVisibleToUser = (boolean) args[0];
                if (isVisibleToUser) {
                    MagicBuriedPoint.onPageOpen(className);
                } else {
                    MagicBuriedPoint.onPageClose(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Around("fragmentV4SetUserVisibleHint()")
    public void aroundJoinV4SetUserVisibleHint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            android.support.v4.app.Fragment target =
                    (android.support.v4.app.Fragment) joinPoint.getTarget();
            String className = target.getClass().getName();
            Object[] args = joinPoint.getArgs();
            if (target.isResumed()) {
                boolean isVisibleToUser = (boolean) args[0];
                if (isVisibleToUser) {
                    MagicBuriedPoint.onPageOpen(className);
                } else {
                    MagicBuriedPoint.onPageClose(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                joinPoint.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
