package lin.leila.petshopinspector.service;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import lin.leila.petshopinspector.MapUtils;
import lin.leila.petshopinspector.ShopDetailActivity;
import lin.leila.petshopinspector.models.PetShop;

/**
 * Created by javiosyc on 2017/3/26.
 */

public class LoadingShopTask extends AsyncTask<Object, PetShop, Void> {

    private GoogleMap map;
    private Context contexts;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Object... params) {

        contexts = (Context) params[0];

        List<PetShop> petShops = (List<PetShop>) params[1];

        map = (GoogleMap) params[2];

        for (PetShop petShop : petShops) {
            publishProgress(petShop);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(PetShop... values) {

        final PetShop petShop = values[0];

        LatLng shopLocation = new LatLng(petShop.getLatitude(), petShop.getLongitude());

        MapUtils.addMarker(map, shopLocation, petShop.getShopName());

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(contexts, ShopDetailActivity.class);
                intent.putExtra(ShopDetailActivity.EXTRA_NAME, petShop);

                contexts.startActivity(intent);
                return false;
            }
        });
    }


}
