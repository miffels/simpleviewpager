package org.simpleviewpager.widget;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * A really simple {@link android.support.v4.view.PagerAdapter} that grabs its contents from the
 * {@link android.support.v4.view.ViewPager} it is provided with. That way the contents may be
 * specified in a purely declarative way (i.e. in the XML file as contents of the pager), which
 * should be a common use case for tutorials and setup wizards.
 * 
 * Event handlers should be set on the pager prior to creating the corresponding <code>
 * SimplePagerAdapter</code>, since <code>findViewById</code> will not work anymore on the
 * <code>ViewPager</code> afterwards!
 * 
 * @author Michael Jess
 *
 */
public class SimplePagerAdapter extends PagerAdapter {
	
	/**
	 * The <code>ViewPager</code> this adapter is bound to.
	 */
	private ViewPager pager;
	
	/**
	 * The subviews of the pager (i.e. those between its start and end tag in
	 * the XML layout). Those pages will be "grabbed" from the pager, removing
	 * them as subviews. 
	 */
	private List<View> pages;

	/**
	 * Creates a new <code>SimpleViewPager</code> for the specified <code>pager
	 * </code> and binds this adapter to it, effectively removing all of its
	 * subviews. If you intend to get references to any of the <code>ViewPager
	 * </code> subviews, you should do so before creating the adapter.
	 * 
	 * @param pager The <code>ViewPager</code> to create the adapter for
	 */
	public SimplePagerAdapter(ViewPager pager) {
		this.setViewPager(pager);
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		if(this.pages == null || position >= this.pages.size()) return null;
		container.addView(this.pages.get(position));
		return this.pages.get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object key) {
		container.removeView(this.pages.get(position));
	}

	@Override
	public int getCount() {
		return this.pages == null ? 0 : this.pages.size();
	}
	
	@Override
	public boolean isViewFromObject(View view, Object sameView) {
		return view == sameView;
	}
	
	/**
	 * Sets the <code>ViewPager</code> for this adapter and binds the adapter
	 * to it, effectively removing all of its subviews. If you intend to get
	 * references to any of the <code>ViewPager</code> subviews, you should do
	 * so before creating the adapter. 
	 * 
	 * @param pager
	 */
	public void setViewPager(ViewPager pager) {
		this.pager = pager;
		this.grabPagerViews();
	}
	
	/**
	 * Strips all subviews off the currently set <code>ViewPager</code> and
	 * stores them as pages in this adapter.
	 */
	private void grabPagerViews() {
		if(this.pager == null) return;
		
		this.pages = new ArrayList<View>(this.pager.getChildCount());
		for(int i = 0; i < this.pager.getChildCount(); i++) {
			this.pages.add(this.pager.getChildAt(i));
		}
		
		Log.d("SimpleViewPager", "Grabbed " + this.pages.size() + " pages.");
		
		this.pager.removeAllViews();
	}
}
