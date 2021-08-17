package com.ybs.base.data.bean;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;


public class DishesProcessEntity {
    //    public String deviceSn;
    public long timestamp;
    public String requestId;
    public String mqttActionEnum;
    public String storeId;
    public List<Status> status;
    public String tableNo;


    public class Status {
        public String dishesId;
        @Nullable
        public String dishesName;
        public int dishesStatus;
        @Nullable
        public String specialDishImgUrl;
//        @Nullable
//        public boolean isActive;//是否推荐


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Status status = (Status) o;
            return dishesStatus == status.dishesStatus &&
                    Objects.equals(dishesId, status.dishesId) &&
                    Objects.equals(dishesName, status.dishesName) &&
                    Objects.equals(specialDishImgUrl, status.specialDishImgUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dishesId, dishesName, dishesStatus, specialDishImgUrl);
        }
    }
}
