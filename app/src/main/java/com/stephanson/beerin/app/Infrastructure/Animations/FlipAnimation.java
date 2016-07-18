package com.stephanson.beerin.app.Infrastructure.Animations;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.text.Layout;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by Stephan on 11.05.2016.
 */
public class FlipAnimation extends Animation {
    private Camera _camera;

    private View _fromView;
    private View _toView;

    private float _centerX;
    private float _centerY;

    private boolean forward = true;

    public FlipAnimation(View fromView, View toView) {
        _fromView = fromView;
        _toView = toView;

        setDuration(700);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void reverse() {
        forward = false;
        View switchView = _toView;
        _toView = _fromView;
        _fromView = switchView;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        _centerX = width / 2;
        _centerY = height / 2;
        _camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        // Angle around the y-axis of the rotation at the given time
        // calculated both in radians and degrees.
        final double radians = Math.PI * interpolatedTime;
        float degrees = (float) (180.0 * radians / Math.PI);

        // Once we reach the midpoint in the animation, we need to hide the
        // source view and show the destination view. We also need to change
        // the angle by 180 degrees so that the destination does not come in
        // flipped around
        if (interpolatedTime >= 0.5f) {
            degrees -= 180.f;
            _fromView.setVisibility(View.GONE);
            _toView.setVisibility(View.VISIBLE);
        }

        if (forward)
            degrees = -degrees; //determines direction of rotation when flip begins

        final Matrix matrix = t.getMatrix();
        _camera.save();
        _camera.rotateX(degrees);
        _camera.getMatrix(matrix);
        _camera.restore();
        matrix.preTranslate(-_centerX, -_centerY);
        matrix.postTranslate(_centerX, _centerY);
    }
}