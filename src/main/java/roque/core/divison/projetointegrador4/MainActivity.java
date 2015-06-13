package roque.core.divison.projetointegrador4;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rey.material.util.ThemeUtil;
import com.rey.material.widget.TabPageIndicator;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements ReusoFrag.OnFragmentInteractionListener {

    private TabPageIndicator tpi;
    private CustomViewPager vp;

    private PagerAdapter mPagerAdapter;
    private DrawerAdapter mDrawerAdapter;


    private Tab[] mItems = new Tab[]{Tab.REUSO,Tab.DICAS, Tab.NIVEL, Tab.ECONOMIA, Tab.SOBRE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tpi= (TabPageIndicator) findViewById(R.id.main_tpi);
        vp= (CustomViewPager) findViewById(R.id.main_vp);



        mDrawerAdapter = new DrawerAdapter();
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mItems);
        vp.setAdapter(mPagerAdapter);
        tpi.setViewPager(vp);

        tpi.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDrawerAdapter.setSelected(mItems[position]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mDrawerAdapter.setSelected(Tab.REUSO);
        vp.setCurrentItem(0);






    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public enum Tab {
        REUSO("Reuso"),
        DICAS("Dicas"),
        NIVEL ("Nível"),
        ECONOMIA ("Economia"),
        SOBRE ("Sobre");

        private final String name;


        private Tab(String s){
            name = s;
        }

        public boolean equalsName(String otherName){
            return (otherName != null) && name.equals(otherName);
        }

        public String toString(){
            return name;
        }

    }



    class DrawerAdapter extends BaseAdapter implements View.OnClickListener {

        private Tab mSelectedTab;

        public void setSelected(Tab tab){
            if(tab != mSelectedTab){
                mSelectedTab = tab;
                notifyDataSetInvalidated();
            }
        }

        public Tab getSelectedTab(){
            return mSelectedTab;
        }

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if(v == null) {
                v = LayoutInflater.from(MainActivity.this).inflate(R.layout.row_drawer, null);
                v.setOnClickListener(this);
            }

            v.setTag(position);
            Tab tab = (Tab)getItem(position);
            ((TextView)v).setText(tab.toString());

            if(tab == mSelectedTab) {
                v.setBackgroundColor(ThemeUtil.colorPrimary(MainActivity.this, 0));
                ((TextView)v).setTextColor(0xFFFFFFFF);
            }
            else {
                v.setBackgroundResource(0);
                ((TextView)v).setTextColor(0xFF000000);
            }

            return v;
        }

        @Override
        public void onClick(View v) {
            int position = (Integer)v.getTag();
            vp.setCurrentItem(position);

        }
    }

    private static class PagerAdapter extends FragmentStatePagerAdapter {

        Fragment[] mFragments;
        Tab[] mTabs;

        private static final Field sActiveField;

        static {
            Field f = null;
            try {
                Class<?> c = Class.forName("android.support.v4.app.FragmentManagerImpl");
                f = c.getDeclaredField("mActive");
                f.setAccessible(true);
            } catch (Exception e) {}

            sActiveField = f;
        }

        public PagerAdapter(FragmentManager fm, Tab[] tabs) {
            super(fm);
            mTabs = tabs;
            mFragments = new Fragment[mTabs.length];


            //dirty way to get reference of cached fragment
            try{
                ArrayList<Fragment> mActive = (ArrayList<Fragment>)sActiveField.get(fm);
                if(mActive != null){
                    for(Fragment fragment : mActive){
                        if(fragment instanceof ReusoFrag)
                            setFragment(Tab.REUSO, fragment);
                        else if(fragment instanceof DicaFragx)
                            setFragment(Tab.DICAS, fragment);
                        else if(fragment instanceof NivelFrag)
                            setFragment(Tab.NIVEL, fragment);
                        else if(fragment instanceof EcoFrag)
                            setFragment(Tab.ECONOMIA, fragment);
                    }
                }
            }
            catch(Exception e){}
        }

        private void setFragment(Tab tab, Fragment f){
            for(int i = 0; i < mTabs.length; i++)
                if(mTabs[i] == tab){
                    mFragments[i] = f;
                    break;
                }
        }

        @Override
        public Fragment getItem(int position) {
            if(mFragments[position] == null){
                switch (mTabs[position]) {
                    case REUSO:
                        mFragments[position] = ReusoFrag.newInstance();
                        break;
                    case DICAS:
                        mFragments[position] = DicaFragx.newInstance();
                        break;
                    case NIVEL:
                        mFragments[position] = NivelFrag.newInstance();
                        break;
                    case ECONOMIA:
                        mFragments[position] = EcoFrag.newInstance();
                        break;
                    case SOBRE:
                        mFragments[position] = SobreFrag.newInstance();
                        break;
                }
            }

            return mFragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs[position].toString().toUpperCase();
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

}

