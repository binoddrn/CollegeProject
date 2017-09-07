package com.learn.binod.navigationdrawer;

/**
 * Created by binod on 4/17/2017.
 */

public class College {
    String id,collegename,address,description,phonenumber;
    private String key;

    public College(String id, String collegename, String address, String description, String phonenumber) {
        this.id = id;
        this.collegename = collegename;
        this.address = address;
        this.description = description;
        this.phonenumber = phonenumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /*
        public void setKey(String key) {
            this.key = key;
        }

        public String getKey(){
            return key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    */
    public void setValues(College newcollege) {
        address=newcollege.address;
        description=newcollege.description;
    }
}
