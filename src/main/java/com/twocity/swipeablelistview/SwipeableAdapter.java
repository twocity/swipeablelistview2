package com.twocity.swipeablelistview;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SwipeableAdapter extends BaseAdapter {
	private SwipeableListView mListView;

	public void setListView(SwipeableListView listview) {
		this.mListView = listview;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v;
		v = newView(parent);
		if (convertView == null) {
			v = newView(parent);
		} else {
			// Do a translation check to test for animation. Change this to
			// something more
			// reliable and robust in the future.
			if (convertView.getTranslationX() != 0
					|| convertView.getTranslationY() != 0) {
				// view was animated, reset
				v = newView(parent);
			} else {
				v = convertView;
			}
		}
		v.setTag(getItem(position));
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListView != null) {
					mListView.onSwipeItemClick(position, v);
				}
			}
		});
		bindView(position, v);
		return v;
	}

	protected abstract View newView(ViewGroup parent);

	protected abstract void bindView(final int position, final View view);
}