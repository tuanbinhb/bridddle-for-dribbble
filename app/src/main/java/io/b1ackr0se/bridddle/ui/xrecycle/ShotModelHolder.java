package io.b1ackr0se.bridddle.ui.xrecycle;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.b1ackr0se.bridddle.R;
import io.b1ackr0se.bridddle.ui.widget.AspectRatioImageView;

public class ShotModelHolder extends XModelHolder {

    @BindView(R.id.shot_image) public AspectRatioImageView shotImageView;
    @BindView(R.id.gif_indicator) public TextView gifIndicator;

    @Override
    public void bindView(View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
