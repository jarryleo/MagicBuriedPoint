package cn.leo.buried.point;

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
            "execution(* onClick(..))";
    private static final String POINTCUT_BUTTER_KNIFE =
            "execution(@butterknife.OnClick * *(..))";
    private static final String POINT_ACTIVITY_ON_CREATE =
            "execution(* android.app.Activity+.onCreate(..))";
    private static final String POINT_FRAGMENT_ON_CREATE =
            "execution(* android.app.Fragment+.onCreate(..))";
    private static final String POINT_FRAGMENT_V4_ON_CREATE =
            "execution(* android.support.v4.app.Fragment+.onCreate(..))";
    private static final String POINT_ACTIVITY_ON_DESTROY =
            "execution(* android.app.Activity+.onDestroy(..))";
    private static final String POINT_FRAGMENT_ON_DESTROY =
            "execution(* android.app.Fragment+.onDestroy(..))";
    private static final String POINT_FRAGMENT_V4_ON_DESTROY =
            "execution(* android.support.v4.app.Fragment+.onDestroy(..))";

    @Pointcut(POINT_ACTIVITY_ON_CREATE)
    public void activityOnCreatePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_ON_CREATE)
    public void fragmentOnCreatePointcut() {

    }

    @Pointcut(POINT_FRAGMENT_V4_ON_CREATE)
    public void fragmentV4OnCreatePointcut() {

    }

    @Pointcut(POINT_ACTIVITY_ON_DESTROY)
    public void activityOnDestroyPointcut() {

    }

    @Pointcut(POINT_FRAGMENT_ON_DESTROY)
    public void fragmentOnDestroyPointcut() {

    }

    @Pointcut(POINT_FRAGMENT_V4_ON_DESTROY)
    public void fragmentV4OnDestroyPointcut() {

    }

    @Pointcut(POINTCUT_ON_CLICK)
    public void methodPointcut() {

    }


    @Pointcut(POINTCUT_BUTTER_KNIFE)
    public void butterKnifePointcut() {

    }

    @Around("methodPointcut() || butterKnifePointcut()")
    public void aroundJoinClickPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
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
        joinPoint.proceed();
    }

    @Around("activityOnCreatePointcut() || fragmentOnCreatePointcut() || fragmentV4OnCreatePointcut()")
    public void aroundJoinPageOpenPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        MagicBuriedPoint.onPageOpen(className);
        joinPoint.proceed();
    }

    @Around("activityOnDestroyPointcut() || fragmentOnDestroyPointcut() || fragmentV4OnDestroyPointcut()")
    public void aroundJoinPageClosePoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        MagicBuriedPoint.onPageClose(className);
        joinPoint.proceed();
    }
}
