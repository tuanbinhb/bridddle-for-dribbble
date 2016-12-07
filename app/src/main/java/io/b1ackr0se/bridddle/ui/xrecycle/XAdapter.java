package io.b1ackr0se.bridddle.ui.xrecycle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class XAdapter extends RecyclerView.Adapter<XViewHolder> {

    private List<XModel<?>> list = new ArrayList<>();
    private final GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            return list.get(position).getSpanSize();
        }
    };

    public XAdapter() {
        setHasStableIds(true);
        spanSizeLookup.setSpanIndexCacheEnabled(true);
    }

    public void addDummy() {
        list.add(null);
        notifyItemInserted(list.size() - 1);
    }

    public void addModels(Collection<? extends XModel<?>> modelsToAdd) {
        int initialSize = list.size();
        list.addAll(modelsToAdd);
        notifyItemRangeInserted(initialSize, modelsToAdd.size());
    }

    public void removeDummy() {
        Object last = list.get(list.size() - 1);
        if (last == null) {
            list.remove(list.size() - 1);
            notifyItemRemoved(list.size() - 1);
        }
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return spanSizeLookup;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getLayoutId();
    }

    @Override
    public XViewHolder onCreateViewHolder(ViewGroup parent, int layoutId) {
        return new XViewHolder(parent, layoutId);
    }

    @Override
    public void onBindViewHolder(XViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getDefaultId();
    }
}
