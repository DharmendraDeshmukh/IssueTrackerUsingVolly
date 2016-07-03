package com.dharmendra;

/**
 * Created by Dharmendra Deshmukh
 *
 */
/*
implements Comparable for Shorting

 */
public class IssuePojo implements   Comparable<IssuePojo>{

    //Data Variables
    private  String issueTitle;
    private String descriptions;
    private String reporterName;
    private  String latestUpdatedTime;

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getIssueTitle() {
        return issueTitle;
    }


    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public String getLatestUpdatedTime() {
        return latestUpdatedTime;
    }

    public void setLatestUpdatedTime(String latestUpdatedTime) {
        this.latestUpdatedTime = latestUpdatedTime;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }


    @Override
    public int compareTo(IssuePojo another) {

        return another.getLatestUpdatedTime().compareTo(getLatestUpdatedTime());

    }
}