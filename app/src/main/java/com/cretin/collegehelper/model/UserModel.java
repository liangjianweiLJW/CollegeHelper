package com.cretin.collegehelper.model;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by cretin on 4/3/16.
 */
public class UserModel extends BmobUser implements Serializable{
    private String id;
    private String nickName;
    private String phoneNum;
    private String psw;
    private String signature;
    private List<UserModel> members;
    private List<VoteModel> joinedVotes;
    private String avater;
    private String age;
    private String sex;
    private String work;
    private String commpany;
    private String school;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCommpany() {
        return commpany;
    }

    public void setCommpany(String commpany) {
        this.commpany = commpany;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public List<VoteModel> getJoinedVotes() {
        return joinedVotes;
    }

    public void setJoinedVotes(List<VoteModel> joinedVotes) {
        this.joinedVotes = joinedVotes;
    }

    public String getSignature() {
        return signature;
    }

    public List<UserModel> getMembers() {
        return members;
    }

    public void setMembers(List<UserModel> members) {
        this.members = members;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        return !(id != null ? !id.equals(userModel.id) : userModel.id != null);

    }
}
