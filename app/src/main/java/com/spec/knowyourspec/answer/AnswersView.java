package com.spec.knowyourspec.answer;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import com.spec.knowyourspec.data.Spec;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class AnswersView extends LinearLayout {
    public AnswersView(Context context) {
        super(context);
        init(context);
    }

    public AnswersView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnswersView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AnswersView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 4; i++) {
            Button button = new Button(context);
            addView(button, i, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }

    public void loadAnswers(List<Spec> specList){
        for (int i = 0; i < specList.size(); i++){
            Button currentButton = (Button) getChildAt(i);
            currentButton.setText(specList.get(i).getFirstName());
        }
    }
}
