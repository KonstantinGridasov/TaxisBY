package com.transport.taxi.bus.taxis.settings.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.transport.taxi.bus.taxis.data.db.DeleteAll;
import com.transport.taxi.bus.taxis.data.db.Fill;
import com.transport.taxi.bus.taxis.data.db.GetListHint;
import com.transport.taxi.bus.taxis.data.db.GetListTaxis;
import com.transport.taxi.bus.taxis.data.db.GetListTaxisOnHalt;
import com.transport.taxi.bus.taxis.data.db.GetTaxisOnHalt;
import com.transport.taxi.bus.taxis.data.db.GetUbdate;
import com.transport.taxi.bus.taxis.data.db.GetVersionUbdate;
import com.transport.taxi.bus.taxis.data.rest.RestApi;
import com.transport.taxi.bus.taxis.data.rest.RestService;
import com.transport.taxi.bus.taxis.data.settingsDb.ReWriteUbdate;
import com.transport.taxi.bus.taxis.data.settingsDb.ReaderJSON;
import com.transport.taxi.bus.taxis.data.settingsDb.WriterHint;
import com.transport.taxi.bus.taxis.data.settingsDb.WriterToDb;
import com.transport.taxi.bus.taxis.domain.entity.usecase.DeleteDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.FillDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListHintDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListTaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListTaxisOnHaltDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetTaxisOnHaltDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetUbdateFromRestDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetVersionUbdateDomain;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GHome on 20.12.2017.
 */
@Module
public class AppModule {

    private Context appContext;

    public AppModule(Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    //Domain

    @Provides
    GetVersionUbdateDomain provideGetVersionUbdateDomain(GetVersionUbdate getVersionUbdate) {
        return new GetVersionUbdateDomain(getVersionUbdate);
    }

    @Provides
    FillDomain provideFilDb(Fill fill) {
        return new FillDomain(fill);
    }

    @Provides
    GetListTaxisDomain provideGetDataBase(GetListTaxis getListTaxis) {
        return new GetListTaxisDomain(getListTaxis);
    }

    @Provides
    GetTaxisOnHaltDomain provideGetOnId(GetTaxisOnHalt getTaxisOnHalt) {
        return new GetTaxisOnHaltDomain(getTaxisOnHalt);
    }

    @Provides
    GetListTaxisOnHaltDomain provideSearchOnDb(GetListTaxisOnHalt getListTaxisOnHalt) {
        return new GetListTaxisOnHaltDomain(getListTaxisOnHalt);
    }

    @Provides
    DeleteDomain provideRemoveALLDb(DeleteAll deleteAll) {
        return new DeleteDomain(deleteAll);
    }

    @Provides
    GetListHintDomain provideGetHintListDb(GetListHint getListHint) {
        return new GetListHintDomain(getListHint);
    }

    @Provides
    GetUbdateFromRestDomain provideGetUbdateFromRest(GetUbdate getUbdate) {
        return new GetUbdateFromRestDomain(getUbdate);
    }


    // Data

    @Provides
    GetUbdate provideGetUbdate(Context context) {
        return new GetUbdate(appContext);
    }

    @Provides
    GetVersionUbdate provideGetVersionUbdate(Context context, RestService restService) {
        return new GetVersionUbdate(appContext, restService);
    }

    @Provides
    Fill provideFil(Context context) {
        return new Fill(appContext);
    }

    @Provides
    GetTaxisOnHalt provideGetHalt(Context context) {
        return new GetTaxisOnHalt(appContext);
    }

    @Provides
    GetListTaxis provideGetList(Context context) {
        return new GetListTaxis(appContext);
    }

    @Provides
    GetListHint provideGetListHint(Context context) {
        return new GetListHint(appContext);
    }

    @Provides
    DeleteAll provideRemoveAll(Context context) {
        return new DeleteAll(appContext);
    }

    @Provides
    GetListTaxisOnHalt provideSearchHalt(Context context) {
        return new GetListTaxisOnHalt(appContext);
    }


    //Rest

    @Singleton
    @Provides
    RestService provideRestService(RestApi restApi) {
        return new RestService(restApi);
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/843CB2B3-5438-080A-FF44-E1231C897A00/B1263850-0FA5-A765-FF4B-B08FD0F0FA00/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();

    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }

    @Provides
    RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    // ReaderWriter

    @Provides
    ReaderJSON provideReaderJSON(Context context) {
        return new ReaderJSON(appContext);
    }

    @Provides
    WriterHint provideWriterHintt(Context context) {
        return new WriterHint(appContext);
    }

    @Provides
    WriterToDb provideWriterToDb(Context context) {
        return new WriterToDb(appContext);
    }


    @Provides
    ReWriteUbdate provideReWrite(Context context) {
        return new ReWriteUbdate(appContext);
    }
}
