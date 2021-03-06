package com.cretin.collegehelper.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cretin.collegehelper.R;
import com.cretin.collegehelper.adapter.VoteMyJoininListViewAdapter;
import com.cretin.collegehelper.model.UserModel;
import com.cretin.collegehelper.model.VoteModel;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

@EActivity(R.layout.activity_vote_my_join_in_un_finish)
public class VoteMyJoinInUnFinishActivity extends AppCompatActivity implements SwipyRefreshLayout.OnRefreshListener{
    @ViewById
    ImageView ivVoteUnfinishedBack;
    @ViewById
    ListView listviewVoteUnfinished;
    @ViewById
    SwipyRefreshLayout swipyListviewVoteUnfinished;

    private List<VoteModel> list;
    private VoteMyJoininListViewAdapter adapter;

    @AfterViews
    public void init(){
        getSupportActionBar().hide();
        list = new ArrayList<>();
        adapter = new VoteMyJoininListViewAdapter(this,list,R.layout.item_listview_vote_my_joinin);
        listviewVoteUnfinished.setAdapter(adapter);

        listviewVoteUnfinished.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VoteMyJoinInUnFinishActivity.this,UserVoteActivity_.class);
                intent.putExtra("info",list.get(position));
                startActivity(intent);
            }
        });

        swipyListviewVoteUnfinished.setOnRefreshListener(this);

        initData();

        ivVoteUnfinishedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoteMyJoinInUnFinishActivity.this.finish();
            }
        });
    }

    private void initData() {
        BmobQuery<UserModel> innerQuery = new BmobQuery<>();
        UserModel users = BmobUser.getCurrentUser(this, UserModel.class);
        String[] friendIds={users.getObjectId()};//好友的objectId数组
        innerQuery.addWhereContainedIn("objectId", Arrays.asList(friendIds));
        //查询帖子
        BmobQuery<VoteModel> query = new BmobQuery<>();
        query.addWhereMatchesQuery("joinList", "_User", innerQuery);
        query.include("author");
        query.findObjects(this, new FindListener<VoteModel>() {
            @Override
            public void onSuccess(List<VoteModel> object) {
                list.clear();
                for (VoteModel v:object) {
                    if(v.getVerifireFlag()==1){
                        list.add(v);
                    }
                }
                adapter.notifyDataSetChanged();
                swipyListviewVoteUnfinished.setRefreshing(false);
            }
            @Override
            public void onError(int code, String msg) {
                Toast.makeText(VoteMyJoinInUnFinishActivity.this, msg, Toast.LENGTH_SHORT).show();
                swipyListviewVoteUnfinished.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        initData();
    }
}
