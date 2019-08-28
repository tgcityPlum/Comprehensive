package com.tgcity.refreshadapter.util;

import android.util.SparseArray;

import com.tgcity.refreshadapter.provider.BaseItemProvider;

/**
 * @author TGCity
 * https://github.com/chaychan
 */

public class ProviderDelegate {

    private SparseArray<BaseItemProvider> mItemProviders = new SparseArray<>();

    public void registerProvider(BaseItemProvider provider){
        if (provider == null){
            throw new ItemProviderException("ItemProvider can not be null");
        }

        int viewType = provider.viewType();

        if (mItemProviders.get(viewType) == null){
            mItemProviders.put(viewType,provider);
        }
    }

    public SparseArray<BaseItemProvider> getItemProviders(){
        return mItemProviders;
    }

}
