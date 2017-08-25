package com.ibuildapp.moveinandmoveout.api.ibaapi;


import com.ibuildapp.moveinandmoveout.model.IbaResponse;
import com.ibuildapp.moveinandmoveout.model.ResultObjectMove;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetResponse;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IBApiSecond {
    @GET("/gdrive-token-refresh")
    Observable<IbaResponse> refreshAccessToken(@Query("module") String moduleId);

    @GET("/gdrive-spreadsheets-reader")
    Call<ResultObjectMove> getAllData(@Query("module") String moduleId, @Query("spreadsheetId") String spreadsheetId,
                                      @Query("ranges[]") List<String> ranges, @Query("majorDimension") String majorDimension);

    @GET("/gdrive-spreadsheets-worksheets-list")
    Call<SheetResponse> getSheetProperties(@Query("module") String moduleId, @Query("spreadsheetId") String spreadsheetId);

    @GET("/gdrive-spreadsheets-update")
    Call<UpdateResponse> updateSpreadsheet(@Query("module") String moduleId,
                                           @Query("spreadsheetId") String spreadsheetId,
                                           @Query("requests") String requests);
}
