package com.example.user.finalbasicproject.control;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.user.finalbasicproject.fragment.DSignUpFragment;
import com.example.user.finalbasicproject.fragment.HSignUpFragment;
import com.example.user.finalbasicproject.utils.Constant;

import java.util.List;
import java.util.Stack;

/**
 * Created by User on 5/9/2017.
 */

public class FragNavControllerSignInUp {
    //Extras used to store savedInstancesState
    private int mContainerID;
    private List<Stack<Fragment>> mFragmentStacks;
    private FragmentManager mFragmentManager;
    Fragment temp;

    public FragNavControllerSignInUp(int IdContainer, FragmentManager manager){
        mFragmentManager = manager;
        mContainerID = IdContainer;
    }

    public void selectFragment(int position){
        switch (position){
            case Constant.ACCOUNT_STUDENT:
                temp = HSignUpFragment.getInstance();
                break;
            case Constant.ACCOUNT_TEACHER:
                temp = DSignUpFragment.getInstance();
                break;
        }
        mFragmentManager.beginTransaction().replace(mContainerID, temp).commit();
    }

    public void removeFragment(){
        temp = HSignUpFragment.getInstance();
        if(temp.isAdded()){
            mFragmentManager.beginTransaction().remove(temp).commit();
        }
        temp = DSignUpFragment.getInstance();
        if(temp.isAdded()){
            mFragmentManager.beginTransaction().remove(temp).commit();
        }
    }
}
