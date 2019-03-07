package com.example.kcb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Coursee> mCourseList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView courseName;
        TextView courseData;
        public ViewHolder(View view){
            super(view);
            courseName = (TextView)view.findViewById(R.id.course_name);
            courseData = (TextView)view.findViewById(R.id.course_data);
        }
    }
    public CourseAdapter(List<Coursee> courseeList){
        mCourseList = courseeList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_buju2,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Coursee course = mCourseList.get(position);
        holder.courseName.setText(course.getName());
        holder.courseData.setText(course.getData());
    }
    @Override
    public int getItemCount(){
        return mCourseList.size();
    }
}

