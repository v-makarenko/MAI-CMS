package ru.vmakarenko.dto.users;

import ru.vmakarenko.dto.common.CommonDto;

import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
public class UserDto extends CommonDto {
    private String name;
    private String surname;
    private String patronymic;
    private String passportSeries;
    private String passportNumber;
    private String email;
    private String password;
    private String userType;
    private String snpShort;
    private String snpLong;
    private UUID wpId;
    private String wpLongName;
    private String wpShortName;

    public UUID getWpId() {
        return wpId;
    }

    public void setWpId(UUID wpId) {
        this.wpId = wpId;
    }

    public String getWpLongName() {
        return wpLongName;
    }

    public void setWpLongName(String wpLongName) {
        this.wpLongName = wpLongName;
    }

    public String getWpShortName() {
        return wpShortName;
    }

    public void setWpShortName(String wpShortName) {
        this.wpShortName = wpShortName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSnpShort() {
        return snpShort;
    }

    public void setSnpShort(String snpShort) {
        this.snpShort = snpShort;
    }

    public String getSnpLong() {
        return snpLong;
    }

    public void setSnpLong(String snpLong) {
        this.snpLong = snpLong;
    }
}
