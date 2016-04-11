package com.example.administrator.weibo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
    private List<String> mFragmentTitles = new ArrayList<String>();
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



        mShowUserHomeFragment=new ShowUserHomeFragment(this);
        mShowUserStatusFragment=new StatusListFragment(this);
        mShowUserAlbumFragment=new ShowUserAlbumFragment();

        mFragmentList.add(mShowUserHomeFragment);
        mFragmentList.add(mShowUserStatusFragment);
        mFragmentList.add(mShowUserAlbumFragment);
        mFragmentTitles.add("主页");
        mFragmentTitles.add("微博");
        mFragmentTitles.add("相册");
        mViewHover = (MyHoveringScrollView) findViewById(R.id.view_hover);
        mViewHover.setTopView(R.id.top);

        currentIndex=0;
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList,mFragmentTitles);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPageVp);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    public static void launch(Context context,User user) {
        Intent intent = new Intent();
        intent.setClass(context, ShowUserActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(AppConstants.WeiboConfig.USER_BUNDLE_KEY, user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
