package com.example.administrator.weibo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.View.MyHoveringScrollView;
import com.example.administrator.weibo.View.WrapContentHeightViewPager;
import com.example.administrator.weibo.adapter.FragmentAdapter;
import com.example.administrator.weibo.common.AppConstants;
import com.example.administrator.weibo.entity.User;
import com.example.administrator.weibo.fragment.ShowUserAlbumFragment;
import com.example.administrator.weibo.fragment.ShowUserHomeFragment;
import com.example.administrator.weibo.fragment.StatusListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class ShowUserActivity extends FragmentActivity {
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private WrapContentHeightViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView tvUserName,tvAttentionFansCount,tvUsersDescription;
    private TextView tvHome,tvStatus,tvAlbum;
    private ImageView imCursor;
    /**
     * Fragment
     */
    private ShowUserHomeFragment  mShowUserHomeFragment;
    private StatusListFragment mShowUserStatusFragment;
    private ShowUserAlbumFragment mShowUserAlbumFragment;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    private MyHoveringScrollView mViewHover;

    private  User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);
        init();

    }
    private void init() {

        mUser = (User)getIntent().getSerializableExtra(AppConstants.WeiboConfig.USER_BUNDLE_KEY);

        tvUserName= (TextView) this.findViewById(R.id.tv_username);
        tvAttentionFansCount= (TextView) this.findViewById(R.id.tv_attention_fans_count);
        tvUsersDescription= (TextView) this.findViewById(R.id.tv_users_description);

        tvUserName.setText(mUser.getScreenName());
        tvAttentionFansCount.setText("关注： "+mUser.getFriendsCount()+" | 粉丝 "+mUser.getFollowersCount());
        tvUsersDescription.setText(mUser.getDescription());

        tvHome = (TextView) this.findViewById(R.id.tv_home);
        tvStatus = (TextView) this.findViewById(R.id.tv_status);
        tvAlbum=(TextView) this.findViewById(R.id.tv_photo_album);
        imCursor = (ImageView) this.findViewById(R.id.iv_cursor);
        mPageVp = (WrapContentHeightViewPager) this.findViewById(R.id.id_page_vp);



        mShowUserHomeFragment=new ShowUserHomeFragment();
        mShowUserStatusFragment=new StatusListFragment(this);
        mShowUserAlbumFragment=new ShowUserAlbumFragment();

        mFragmentList.add(mShowUserHomeFragment);
        mFragmentList.add(mShowUserStatusFragment);
        mFragmentList.add(mShowUserAlbumFragment);

        mViewHover = (MyHoveringScrollView) findViewById(R.id.view_hover);
        mViewHover.setTopView(R.id.top);

        currentIndex=0;
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
        mPageVp.setOnPageChangeListener(new MyOnPageChangeListener());

        setPressed(0);
        Log.d("hhhhhhhh", mPageVp.getHeight() + "");
        setClickListener(tvHome, 0);
        setClickListener(tvStatus,1);
        setClickListener(tvAlbum,2);

    }

    private void setClickListener(TextView textView,final int i)
    {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hh",imCursor.getLeft()+"");
                if (mPageVp.getCurrentItem() != i) {
                    reset();
                    setPressed(i);
                    mPageVp.setCurrentItem(i);
                    currentIndex=i;
                }
            }
        });
    }
    private void reset() {
        tvHome.setTextColor(getResources().getColor(R.color.gray));
        tvStatus.setTextColor(getResources().getColor(R.color.gray));
        tvAlbum.setTextColor(getResources().getColor(R.color.gray));
    }
    private void setPressed(int i)
    {
        switch (i) {
            case 0:
                tvHome.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                tvStatus.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                tvAlbum.setTextColor(getResources().getColor(R.color.black));
                break;
        }

    }
    public static void launch(Context context,User user) {
        Intent intent = new Intent();
        intent.setClass(context, ShowUserActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(AppConstants.WeiboConfig.USER_BUNDLE_KEY, user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        float zero,one,two;
        @Override
        public void onPageSelected(int arg0) {

            float endPoint=0;

            int[] homePosition = new int[2];

            tvHome.getLocationInWindow(homePosition);
            int[] statusPosition = new int[2];
            tvStatus.getLocationInWindow(statusPosition);
            int[] albumPosition = new int[2];
            tvAlbum.getLocationInWindow(albumPosition);

            zero=tvHome.getLeft()-tvHome.getLeft();
            one=tvStatus.getLeft()-tvHome.getLeft();
            two=tvAlbum.getLeft()-tvHome.getLeft();


            Animation animation = null;
            switch(arg0) {
                case 0:
                    if(currentIndex == 1) {

                        animation = new TranslateAnimation(one,zero , 0, 0);
                    } else if(currentIndex == 2) {
                        animation = new TranslateAnimation(two,zero , 0, 0);
                    }
                    endPoint=tvHome.getX();
                    break;
                case 1:
                    if(currentIndex == 0) {
                        animation = new TranslateAnimation(zero,one , 0, 0);
                    } else if(currentIndex == 2) {
                        animation = new TranslateAnimation(two,one , 0, 0);
                    }
                    endPoint=tvStatus.getX();
                    break;
                case 2:
                    if(currentIndex == 0) {
                        animation = new TranslateAnimation(zero,two , 0, 0);
                    } else if(currentIndex == 1) {
                        animation = new TranslateAnimation(one,two , 0, 0);
                    }
                    endPoint=tvAlbum.getX();
                    break;
            }
            currentIndex = arg0;
            animation.setFillAfter(true);//True:图片停在动画结束位置
            animation.setDuration(300);
            setAnimationsListener(animation,imCursor,endPoint);
            imCursor.startAnimation(animation);
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    private void setAnimationsListener(Animation anim, final ImageView imCursor, final float endPoint) {
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                imCursor.clearAnimation();
                imCursor.setX(endPoint);
            }
        });
    }
}
