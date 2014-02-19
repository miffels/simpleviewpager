package org.simpleviewpager.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Convenience class that automatically sets its own <code>SimpleViewPager
 * </code>. Note that the <code>SimpleViewPager</code> removes all of the
 * pager's subviews, so that you will be unable to use <code>findViewById
 * </code> when using this class.
 * 
 * @author Michael Jess
 *
 */
public class SimpleViewPager extends ViewPager {

	
	/**
	 * Constructor used by layout inflaters.
	 * 
	 * @param context The parent context
	 * @param attrs The view attributes
	 */
	public SimpleViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void onFinishInflate() {
		this.setAdapter(new SimplePagerAdapter(this));
		this.setCurrentItem(0);
	}
	
}
