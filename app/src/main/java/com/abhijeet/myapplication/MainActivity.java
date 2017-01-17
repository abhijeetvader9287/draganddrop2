package com.abhijeet.myapplication;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MainActivity extends AppCompatActivity  implements View.OnTouchListener,View.OnDragListener {
    ImageView tennisball;
    ImageView rugbyball;
    ImageView soccerball;
    LinearLayout top_container;
    LinearLayout bottom_container;
    Drawable enterShape;
    Drawable normalShape  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          enterShape = getResources().getDrawable(
                R.drawable.shape_droptarget);
          normalShape = getResources().getDrawable(R.drawable.shape);
        findViewById(R.id.myimage1).setOnTouchListener( this);
        findViewById(R.id.bottomright).setOnDragListener( this);
        findViewById(R.id.myimage2).setOnTouchListener( this);
        findViewById(R.id.topleft).setOnDragListener( this);
        findViewById(R.id.myimage3).setOnTouchListener( this);
        findViewById(R.id.topright).setOnDragListener( this);
        findViewById(R.id.myimage4).setOnTouchListener( this);
        findViewById(R.id.bottomleft).setOnDragListener( this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);
            view.startDrag(data, shadowBuilder, view, 0);
        /*    view.setVisibility(View.INVISIBLE);*/
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundDrawable(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
              /*  view.setVisibility(View.VISIBLE);*/
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundDrawable(normalShape);
            default:
                break;
        }
        return true;
    }
}
