package com.transport.taxi.bus.taxis.settings.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.transport.taxi.bus.taxis.data.db.DeleteAllFromDb;
import com.transport.taxi.bus.taxis.data.db.FillInDb;
import com.transport.taxi.bus.taxis.data.db.GetFromDb;
import com.transport.taxi.bus.taxis.data.db.GetFromNet;
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
import com.transport.taxi.bus.taxis.domain.entity.usecase.SetVersionUbdateDomain;

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
    GetVersionUbdateDomain provideGetVersionUbdateDomain(GetFromNet getFromNet) {
        return new GetVersionUbdateDomain(getFromNet);
    }

    @Provides
    GetUbdateFromRestDomain provideGetUbdateFromRest(GetFromNet getFromNet) {
        return new GetUbdateFromRestDomain(getFromNet);
    }

    @Provides
    SetVersionUbdateDomain provideSetVersionUbdateDomain(GetFromNet getFromNet) {
        return new SetVersionUbdateDomain(getFromNet);
    }

    @Provides
    FillDomain provideFilDb(FillInDb fillInDb) {
        return new FillDomain(fillInDb);
    }

    @Provides
    GetListTaxisDomain provideGetDataBase(GetFromDb getFromDb) {
        return new GetListTaxisDomain(getFromDb);
    }

    @Provides
    GetTaxisOnHaltDomain provideGetOnId(GetFromDb getFromDb) {
        return new GetTaxisOnHaltDomain(getFromDb);
    }

    @Provides
    GetListTaxisOnHaltDomain provideSearchOnDb(GetFromDb getFromDb) {
        return new GetListTaxisOnHaltDomain(getFromDb);
    }

    @Provides
    DeleteDomain provideRemoveALLDb(DeleteAllFromDb deleteAllFromDb) {
        return new DeleteDomain(deleteAllFromDb);
    }

    @Provides
    GetListHintDomain provideGetHintListDb(GetFromDb getListHint) {
        return new GetListHintDomain(getListHint);
    }


    // Data

    @Provides
    GetFromDb provideGetFromDb(Context context) {
        return new GetFromDb(appContext);
    }

    @Provides
    GetFromNet provideGetFromNet(Context context, RestService restService) {
        return new GetFromNet(appContext, restService);
    }

    @Provides
    FillInDb provideFil(Context context) {
        return new FillInDb(appContext);
    }


    @Provides
    DeleteAllFromDb provideRemoveAll(Context context) {
        return new DeleteAllFromDb(appContext);
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
                .baseUrl("https://api.backendless.com/B744EA4C-80EA-94BC-FFB2-8B4EA665D800/3E1D86FC-60F8-8072-FFEB-5D9AEB099600/")
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
