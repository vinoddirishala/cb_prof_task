package com.chotabeta.ptask.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chotabeta.ptask.beans.Data;
import com.chotabeta.ptask.repository.DataRepo;

import java.util.ArrayList;

/**
 * Created by Vinod Dirishala on 20-02-2021 12:16
 **/
public class DataViewModel extends ViewModel {

    private DataRepo dataRepo;
    private MutableLiveData<ArrayList<Data.DataBean>> mutableLiveData;

    public DataViewModel() {
       dataRepo = new DataRepo();
    }

    public LiveData<ArrayList<Data.DataBean>> getDataFromRepo(){
        if(mutableLiveData==null){
            mutableLiveData = dataRepo.getData();
        }
        return mutableLiveData;
    }

}
