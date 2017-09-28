package ua.com.superdeal.githubsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Repo {

    @SerializedName("groups")
    @Expose
    private List<Organizations> organizationsList = new ArrayList<Organizations>();

    public List<Organizations> getOrganizationsList() {
        return organizationsList;
    }

    public void setOrganizationsList(List<Organizations> organizationsList) {
        this.organizationsList = organizationsList;
    }

    public Repo withOrganizations(List<Organizations> organizationsList) {
        this.organizationsList=organizationsList;
        return this;
    }
}