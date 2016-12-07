package io.b1ackr0se.bridddle.ui.xrecycle;


import android.support.annotation.LayoutRes;

public abstract class XModel<T extends XModelHolder> {
    @LayoutRes private int layoutResId;
    private int id = (int) (System.currentTimeMillis() / 1000);
    private int size = 1;


    public int getDefaultId() {
        return id;
    }

    public void setSpanSize(int spanSize) {
        size = spanSize;
    }

    public int getSpanSize() {
        return size;
    }

    @LayoutRes
    public int getLayoutId() {
        return layoutResId;
    }

    public void setLayoutId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }

    public abstract T createHolder();

    public abstract void bind(T holder);
}
