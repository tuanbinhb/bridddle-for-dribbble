package io.b1ackr0se.bridddle.ui.xrecycle;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public class XViewHolder extends RecyclerView.ViewHolder {
    private XModelHolder holder;

    public XViewHolder(ViewGroup viewGroup, @LayoutRes int layoutId) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));
        ButterKnife.bind(this, itemView);
    }

    public void bind(XModel model) {
        if (holder == null) {
            holder = model.createHolder();
        }
        //noinspection unchecked
        model.bind(holder);
    }
}
