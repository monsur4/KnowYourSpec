package com.spec.knowyourspec.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = DatabaseConstants.TABLE_NAME)
public class Spec {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseConstants.ID)
    private int id;

    @ColumnInfo(name = "firstName")
    private String firstName;
    private String lastName;
    private String alias;
    private String email;
    private String bestSpec;
    private String favoriteLecturer;
    private String alternateCareer;
    private String likelySpecialty;
    private String favoriteQuote;

    public Spec(int id, String firstName, String lastName, String alias, String email,
                String bestSpec, String favoriteLecturer, String alternateCareer,
                String likelySpecialty, String favoriteQuote) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.email = email;
        this.bestSpec = bestSpec;
        this.favoriteLecturer = favoriteLecturer;
        this.alternateCareer = alternateCareer;
        this.likelySpecialty = likelySpecialty;
        this.favoriteQuote = favoriteQuote;
    }

    @Ignore
    public Spec(String firstName, String lastName, String alias, String email,
                String bestSpec, String favoriteLecturer, String alternateCareer,
                String likelySpecialty, String favoriteQuote) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.email = email;
        this.bestSpec = bestSpec;
        this.favoriteLecturer = favoriteLecturer;
        this.alternateCareer = alternateCareer;
        this.likelySpecialty = likelySpecialty;
        this.favoriteQuote = favoriteQuote;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAlias() {
        return alias;
    }

    public String getEmail() {
        return email;
    }

    public String getBestSpec() {
        return bestSpec;
    }

    public String getFavoriteLecturer() {
        return favoriteLecturer;
    }

    public String getAlternateCareer() {
        return alternateCareer;
    }

    public String getLikelySpecialty() {
        return likelySpecialty;
    }

    public String getFavoriteQuote() {
        return favoriteQuote;
    }
}
