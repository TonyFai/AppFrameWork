package com.dxtdkwt.zzh.appframework.view;

import android.animation.TypeEvaluator;

public class IntEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (startInt + fraction * (endValue - startInt));
    }
}
