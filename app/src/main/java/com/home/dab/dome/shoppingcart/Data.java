package com.home.dab.dome.shoppingcart;

import com.home.dab.dome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAB on 2016/12/13 14:10.
 */

public class Data {
    private String title;
    private int selectSum;
    private int id;
    private List<Commodity> mCommodities;

    public Data(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelectSum() {
        return selectSum;
    }

    public void setSelectSum(int selectSum) {
        this.selectSum = selectSum;
    }

    public List<Commodity> getCommodities() {
        if (mCommodities == null) {
            mCommodities = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                mCommodities.add(new Commodity(id,title,"商品" + i, "www.baidu.com", i + 10, R.mipmap.ic_launcher + ""));
            }
        }

        return mCommodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        mCommodities = commodities;
    }

    public static class Commodity {
        private String name;
        private String url;
        private double commodity;
        private String iconUrl;
        private int ownerId;
        private String owenerTitle;

        public Commodity(int ownerId, String owenerTitle, String name, String url, double commodity, String iconUrl) {
            this.owenerTitle = owenerTitle;
            this.ownerId = ownerId;
            this.name = name;
            this.url = url;
            this.commodity = commodity;
            this.iconUrl = iconUrl;
        }

        public String getOwenerTitle() {
            return owenerTitle;
        }

        public void setOwenerTitle(String owenerTitle) {
            this.owenerTitle = owenerTitle;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public double getCommodity() {
            return commodity;
        }

        public void setCommodity(double commodity) {
            this.commodity = commodity;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
    }

}
