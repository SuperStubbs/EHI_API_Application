package ehi.ehiapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Repo {
    @SerializedName("name")
    @Expose
    private String repoName;

    @SerializedName("updated_at")
    @Expose
    private String lastUpdatedDate;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("created_at")
    @Expose
    private String createdDate;

    @SerializedName("git_url")
    @Expose
    private String repoLink;

    @SerializedName("license")
    @Expose
    private License license;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getLastUpdatedDate() {
        return getDateFormatted(lastUpdatedDate);
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return getDateFormatted(createdDate);
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRepoLink() {
        return repoLink;
    }

    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    private String getDateFormatted(String dateToFormat){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String strDateTime = "";
        try {
            Date da = inputFormat.parse(dateToFormat);
            strDateTime = outputFormat.format(da);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strDateTime;
    }
}
