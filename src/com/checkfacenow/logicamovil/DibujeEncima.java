package com.checkfacenow.logicamovil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class DibujeEncima extends View { 
	    private int screenCenterX = 0;
	    private int screenCenterY = 0;
	    private static double centroX = 0;
	    private static double centroY = 0;
	    final int radius = 30;
	    public DibujeEncima(Context context, int screenCenterX, int screenCenterY) { 
	    	super(context); 
	        this.screenCenterX = screenCenterX;
	        this.screenCenterY = screenCenterY;
	     } 

	    @Override 
	    protected void onDraw(Canvas canvas) { 
	    	Paint p = new Paint();
	        p.setColor(Color.BLUE);
	        canvas.drawLine(screenCenterX, 0, screenCenterX, screenCenterY-120, p);
	        canvas.drawLine(0, screenCenterY-120, screenCenterX*2, screenCenterY-120, p);
	        p.setColor(Color.WHITE);
	        DashPathEffect dashPath = new DashPathEffect(new float[]{5,5}, (float)200.0);
	        p.setPathEffect(dashPath);
	        p.setStyle(Style.STROKE);
	        RectF oval = new RectF(screenCenterX-150, screenCenterY-300,screenCenterX+150,screenCenterY+100);
	        canvas.drawOval(oval, p);
	        p.setColor(Color.GREEN);
	        canvas.drawCircle(screenCenterX, screenCenterY-30, 15, p);
	        super.onDraw(canvas);
	    }

		public static void setCentroRostro(double x, double y) {
			centroX = x;
			centroY = y;
		}
}
