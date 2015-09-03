package com.boardgamegeek.filterer;

import android.content.Context;
import android.content.res.Resources;

import com.boardgamegeek.R;
import com.boardgamegeek.provider.BggContract.Collection;

public class MyRatingFilterer extends CollectionFilterer {
	public static final double MIN_RANGE = 0.0;
	public static final double MAX_RANGE = 10.0;

	private static final String delimiter = ":";

	private double mMin;
	private double mMax;

	public MyRatingFilterer() {
		setType(CollectionFilterDataFactory.TYPE_MY_RATING);
	}

	public MyRatingFilterer(Context context, double min, double max) {
		mMin = min;
		mMax = max;
		init(context);
	}

	public MyRatingFilterer(Context context, String data) {
		String[] d = data.split(delimiter);
		mMin = Double.valueOf(d[0]);
		mMax = Double.valueOf(d[1]);
		init(context);
	}

	@Override
	public String flatten() {
		return String.valueOf(mMin) + delimiter + String.valueOf(mMax);
	}

	public double getMax() {
		return mMax;
	}

	public double getMin() {
		return mMin;
	}

	private void init(Context context) {
		setType(CollectionFilterDataFactory.TYPE_MY_RATING);
		setDisplayText(context.getResources());
		setSelection();
	}

	private void setDisplayText(Resources r) {
		String minValue = String.valueOf(mMin);
		String maxValue = String.valueOf(mMax);

		String text;
		if (mMin == mMax) {
			text = maxValue;
		} else {
			text = minValue + "-" + maxValue;
		}
		displayText(r.getString(R.string.my_rating) + " " + text);
	}

	private void setSelection() {
		String minValue = String.valueOf(mMin);
		String maxValue = String.valueOf(mMax);

		String selection;
		if (mMin == mMax) {
			selection = Collection.RATING + "=?";
			selectionArgs(minValue);
		} else {
			selection = "(" + Collection.RATING + ">=? AND " + Collection.RATING + "<=?)";
			selectionArgs(minValue, maxValue);
		}
		selection(selection);
	}
}
