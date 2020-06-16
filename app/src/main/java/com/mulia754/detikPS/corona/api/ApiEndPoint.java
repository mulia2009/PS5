package com.mulia754.detikPS.corona.api;

import com.mulia754.detikPS.corona.model.CountryModel;
import com.mulia754.detikPS.corona.model.RingkasanModel;
import com.mulia754.detikPS.corona.model.RiwayatModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Azhar Rivaldi on 20/03/2020.
 */

public interface ApiEndPoint {

    @GET(Api.END_POINT_WORLD_HISTORY)
    Call<List<RiwayatModel>> getHistoryList(@Path("date") String date);

    @GET(Api.END_POINT_SUMMARY_WORLD)
    Call<RingkasanModel> getSummaryWorld();

    @GET(Api.END_POINT_IDN)
    Call<CountryModel> getSummaryIdn();

}
