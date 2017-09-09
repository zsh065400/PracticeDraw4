package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    private Camera mCamera = new Camera();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = bitmap.getWidth() / 2;
        int centerY = bitmap.getHeight() / 2;

        canvas.save();
        mCamera.save();
        mCamera.rotateX(30);
        canvas.translate(point1.x + centerX, point1.y + centerY);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-(point1.x + centerX), -(point1.y + centerY));
        mCamera.restore();
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        final Matrix matrix = new Matrix();
        mCamera.save();
        matrix.reset();
        mCamera.rotateY(30);
        mCamera.getMatrix(matrix);
        mCamera.restore();
        matrix.preTranslate(-(point2.x + centerX), -(point2.y + centerY));
        matrix.postTranslate(point2.x + centerX, point2.y + centerY);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
