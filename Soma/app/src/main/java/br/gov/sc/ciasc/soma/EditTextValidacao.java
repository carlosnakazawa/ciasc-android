package br.gov.sc.ciasc.soma;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextValidacao extends EditText {

    public EditTextValidacao(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isValid() {
        String input = this.getText().toString();
        Drawable drawable = getResources().getDrawable(android.R.drawable.presence_online);
        if (input.trim().isEmpty()) {
            drawable = getResources().getDrawable(android.R.drawable.presence_offline);
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            return false;
        }
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return true;
    }
}
