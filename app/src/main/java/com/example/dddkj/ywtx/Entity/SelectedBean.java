package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/24 16:40
 */

public class SelectedBean extends Selected{


    public SelectedBean(AddressListData data,boolean b) {
        this.mAddressListData =data;
        setSelected(b);
    }


    public AddressListData getAddressListData() {
        return mAddressListData;
    }

    public void setAddressListData(AddressListData addressListData) {
        mAddressListData = addressListData;
    }

    private AddressListData mAddressListData;

}
