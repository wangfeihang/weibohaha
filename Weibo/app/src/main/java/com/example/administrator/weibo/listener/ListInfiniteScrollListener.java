/*
 * Copyright (C) 2016 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.weibo.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.weibo.adapter.MyBaseAdapter;
import com.example.administrator.weibo.common.AppConstants;

import java.util.List;

/**
 * InfiniteScrollListener, which can be added to RecyclerView with addOnScrollListener
 * to detect moment when RecyclerView was scrolled to the end.
 */
public abstract class  ListInfiniteScrollListener extends RecyclerView.OnScrollListener {
   LinearLayoutManager mLinearLayoutManager;
  /**
   * Initializes InfiniteScrollListener, which can be added
   * to RecyclerView with addOnScrollListener method
   *
   * @param maxItemsPerRequest Max items to be loaded in a single request.
   * @param layoutManager LinearLayoutManager created in the Activity.
   */
  public ListInfiniteScrollListener(LinearLayoutManager linearLayoutManager) {
    mLinearLayoutManager=linearLayoutManager;
  }

  @Override
  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    Log.d("state", "a");
    //得到当前显示的最后一个item的view
    View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
    //得到lastChildView的bottom坐标值
    int lastChildBottom = lastChildView.getBottom();
    //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
    int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();
    //通过这个lastChildView得到这个view当前的position值
    int lastPosition  = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

    //判断lastChildView的bottom值跟recyclerBottom
    //判断lastPosition是不是最后一个position
    //如果两个条件都满足则说明是真正的滑动到了底部
    Log.d("state_lastChildBottom",lastChildBottom+"");
    Log.d("state_recyclerBottom",recyclerBottom+"");
    Log.d("state_lastPosition", lastPosition + "");
    Log.d("state_count", recyclerView.getLayoutManager().getItemCount()-1+"");
    if(lastPosition == recyclerView.getLayoutManager().getItemCount()-1 ){
      onScrolledToEnd();
    }
  }

  public abstract void onScrolledToEnd();
}
