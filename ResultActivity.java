package com.example.directions.Activity;

import static com.example.directions.Activity.ListViewActivity.markerImage;
import static com.example.directions.Activity.MainActivity.btnClickSize;
import static com.example.directions.ArraySavingClass.array_saving_class.alTMapPoint;
import static com.example.directions.ArraySavingClass.array_saving_class.autoZoomLocation;
import static com.example.directions.ArraySavingClass.array_saving_class.buttonNumArr;
import static com.example.directions.ArraySavingClass.array_saving_class.nameOfIt;
import static com.example.directions.ArraySavingClass.array_saving_class.buttonNum;
import static com.example.directions.ArraySavingClass.array_saving_class.buttonNumArr;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.directions.ArraySavingClass.array_saving_class;
import com.example.directions.Directions_Mark;
import com.example.directions.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.poi_item.TMapPOIItem;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity{
    private final String TMAP_API_KEY = "WggO9W2KT1HepbHAYgbt76NghWmyn5S5XASd57Xd";

    TMapData tmapdata;
    TMapView tmap;
    Bitmap centerMarkerImage;
    TMapPoint centerP;
    TextView subway_tv;
    TextView time_tv;

    double leftTopLat = 0;
    double leftTopLon = 0;
    double rightBottomLat = 0;
    double rightBottomLon = 0;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tmap = new TMapView(this);
        LinearLayout tmaplayout = (LinearLayout)findViewById(R.id.main_layout_tmap);
        ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton);
        time_tv = (TextView)findViewById(R.id.estimated_time);
        subway_tv = (TextView)findViewById(R.id.subway);
        Button cafebtn = (Button) findViewById(R.id.button1);
        Button foodbtn = (Button) findViewById(R.id.button2);
        Button studybtn = (Button) findViewById(R.id.button3);
        Button etcbtn = (Button) findViewById(R.id.button4);

        tmaplayout.addView(tmap);

        centerMarkerImage = BitmapFactory.decodeResource(this.getResources(),R.drawable.marker_icon);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity. class);
                startActivity(intent);
            }
        });

        /*tmapdata = new TMapData();
        tmapdata.convertGpsToAddress(array_saving_class.center_point.getLatitude(), array_saving_class.center_point.getLongitude(), new TMapData.ConvertGPSToAddressListenerCallback() {
            @Override
            public void onConvertToGPSToAddress(String s) {
                result_tv.setText(s);
            }
        });*/

        TMapMarkerItem center = new TMapMarkerItem();


        centerP = new TMapPoint(array_saving_class.center_point.getLatitude(), array_saving_class.center_point.getLongitude());

        center.setIcon(centerMarkerImage); // 중간지점 마커 아이콘 지정
        center.setCanShowCallout(true); // 풍선뷰의 사용여부를 설정(사용).
        //center.setCalloutSubTitle("중간지점 입니다."); // 풍선뷰 보조메시지 설정

        /*leftTopLat = centerP.getLatitude();
        leftTopLon = centerP.getLongitude();
        rightBottomLat = centerP.getLatitude();
        rightBottomLon = centerP.getLongitude();

        // 자동으로 줌을 해주기 위해 leftTop의 위도를 설정
        for(int i = 0; i< buttonNumArr.size(); i++) {
            if(autoZoomLocation[buttonNumArr.get(i)].getLatitude() <= leftTopLat) {

                leftTopLat = autoZoomLocation[buttonNumArr.get(i)].getLatitude();
            } else{ }
        }
        // 자동으로 줌을 해주기 위해 leftTop의 경도를 설정
        for(int i = 0; i< buttonNumArr.size(); i++) {
            if(autoZoomLocation[buttonNumArr.get(i)].getLongitude() >= leftTopLon) {
                leftTopLon = autoZoomLocation[buttonNumArr.get(i)].getLongitude();
            } else{ }
        }
        // 자동으로 줌을 해주기 위해 rightBottom 위도를 설정
        for(int i = 0; i< buttonNumArr.size(); i++) {
            if(autoZoomLocation[buttonNumArr.get(i)].getLatitude() >= rightBottomLat){
                rightBottomLat = autoZoomLocation[buttonNumArr.get(i)].getLatitude();
            } else{ }
        }
        // 자동으로 줌을 해주기 위해 rightBottom 경도를 설정
        for(int i = 0; i< buttonNumArr.size(); i++) {
            if(autoZoomLocation[buttonNumArr.get(i)].getLongitude() <= rightBottomLon ) {
                rightBottomLon = autoZoomLocation[buttonNumArr.get(i)].getLongitude();
            } else{ }
        }

        TMapPoint leftTop = new TMapPoint(leftTopLat, leftTopLon);
        TMapPoint rightBottom = new TMapPoint(rightBottomLat, rightBottomLon);*/


        center.setCalloutTitle("중간지점 "); // 풍선뷰 제목 설정
        center.setAutoCalloutVisible(true); // 풍선뷰 자동 활성화

        // 마커의 좌표 지정
        center.setTMapPoint(centerP); // 마커 위,경도 설정
        center.setVisible(TMapMarkerItem.VISIBLE); // 마커 아이콘을 보이게 설정



        //지도에 마커 추가
        //tmap.addMarkerItem("markerItem", center);
        //tmap.setCenterPoint(centerP.getLongitude(), centerP.getLatitude()); // 위도경도 바꿔서

        //자동으로 최적화된 줌을 해줌
        //tmap.zoomToTMapPoint(leftTop, rightBottom );

        // 중간지점에 자동차경로 추가
        //Dutch_Mark.polyLineReturn(tMapView, alTMapPoint_size, centerP);

        // 모든마크 표시
        Directions_Mark.allMarkReturn(markerImage, tmap, alTMapPoint, nameOfIt, buttonNum, btnClickSize, buttonNumArr);

        TMapData forFindStation = new TMapData(); // 인근 지하철을 찾은 데이터를 넣을 TMapData
        TMapPoint centerPoint = centerP;


        //주변에 있는 지하철역을 검색
        forFindStation.findAroundNamePOI(centerPoint, "지하철", 3, 1, new TMapData.FindAroundNamePOIListenerCallback() {
            @Override
            public void onFindAroundNamePOI(ArrayList<TMapPOIItem> POI_item) {
                try{
                    if(POI_item != null) {
                        subway_tv.setText("인근 지하철 역 : " + POI_item.get(0).getPOIName()); // 중간지점에서 가장 가까운 지하철역을 textView에 보여줌
                        TMapPoint point = POI_item.get(0).getPOIPoint();
                        TMapMarkerItem marker = new TMapMarkerItem();
                        marker.setTMapPoint(point);
                        marker.setName(POI_item.get(0).getPOIName());
                        marker.setVisible(TMapMarkerItem.VISIBLE);
                        tmap.addMarkerItem(POI_item.get(0).getPOIName(), marker);
                        tmap.setCenterPoint(marker.longitude, marker.latitude); // 위도경도 바꿔서

                        marker.setCalloutTitle(POI_item.get(0).getPOIName());
                        marker.setCanShowCallout(true);
                        marker.setIcon(centerMarkerImage);
                    }
                    else {
                        subway_tv.setText("인근 지하철 역 정보가 없습니다."); // 중간지점에서 주변 지하철을 검색했을 때 결과값이 하나도 없다면 textView에 지하철 역 정보가 없다고 알려줌
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });






        cafebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getAroundBizPoi1(); }
        });
        foodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAroundBizPoi2();
            }
        });
        studybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAroundBizPoi3();
            }
        });
        etcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAroundBizPoi4();
            }
        });
    }


    public void getAroundBizPoi1() {
        tmap.removeAllMarkerItem();
        TMapData tmapdata = new TMapData();

        TMapPoint point = tmap.getCenterPoint();

        TMapPoint centerPoint = new TMapPoint(centerP.getLatitude(), centerP.getLongitude());
        tmapdata.findAroundNamePOI(centerPoint, "카페", 3, 20,
                new TMapData.FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems != null) {
                            for (TMapPOIItem item : poiItems) {

                                TMapPoint point = item.getPOIPoint();
                                TMapMarkerItem marker = new TMapMarkerItem();
                                marker.setTMapPoint(point);
                                marker.setName(item.getPOIName());
                                marker.setVisible(TMapMarkerItem.VISIBLE);

                                tmap.addMarkerItem(item.getPOIName(), marker);

                                marker.setCalloutTitle("카페");
                                marker.setCalloutSubTitle(marker.getName());
                                marker.setCanShowCallout(true);
                                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cafe1);
                                int newWidth = 70;
                                int newHeight = 70;
                                Bitmap scaledBitmap1 = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                                marker.setIcon(scaledBitmap1);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "카페가 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void getAroundBizPoi2() {
        tmap.removeAllMarkerItem();
        TMapData tmapdata = new TMapData();

        TMapPoint point = tmap.getCenterPoint();

        TMapPoint centerPoint = new TMapPoint(centerP.getLatitude(), centerP.getLongitude());
        tmapdata.findAroundNamePOI(centerPoint, "음식점", 3, 10,
                new TMapData.FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems != null) {
                            for (TMapPOIItem item : poiItems) {

                                TMapPoint point = item.getPOIPoint();

                                TMapMarkerItem marker = new TMapMarkerItem();
                                marker.setTMapPoint(point);
                                marker.setName(item.getPOIName());
                                marker.setVisible(TMapMarkerItem.VISIBLE);

                                tmap.addMarkerItem(item.getPOIName(), marker);

                                marker.setCalloutTitle("음식점");
                                marker.setCalloutSubTitle(marker.getName());
                                marker.setCanShowCallout(true);
                                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.food);
                                int newWidth = 70;
                                int newHeight = 70;
                                Bitmap scaledBitmap1 = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                                marker.setIcon(scaledBitmap1);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "음식점이 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void getAroundBizPoi3() {
        tmap.removeAllMarkerItem();
        TMapData tmapdata = new TMapData();

        TMapPoint point = tmap.getCenterPoint();

        TMapPoint centerPoint = new TMapPoint(centerP.getLatitude(), centerP.getLongitude());
        tmapdata.findAroundNamePOI(centerPoint, "스터디카페", 3, 10,
                new TMapData.FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems != null) {
                            for (TMapPOIItem item : poiItems) {

                                TMapPoint point = item.getPOIPoint();

                                TMapMarkerItem marker = new TMapMarkerItem();
                                marker.setTMapPoint(point);
                                marker.setName(item.getPOIName());
                                marker.setVisible(TMapMarkerItem.VISIBLE);

                                tmap.addMarkerItem(item.getPOIName(), marker);

                                tmap.addMarkerItem(item.getPOIName(), marker);
                                marker.setCalloutTitle("스터디카페");
                                marker.setCalloutSubTitle(marker.getName());
                                marker.setCanShowCallout(true);
                                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.study);
                                int newWidth = 70;
                                int newHeight = 70;
                                Bitmap scaledBitmap3 = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                                marker.setIcon(scaledBitmap3);

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "스터디카페가 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void getAroundBizPoi4() {
        tmap.removeAllMarkerItem();
        TMapData tmapdata = new TMapData();

        TMapPoint point = tmap.getCenterPoint();

        TMapPoint centerPoint = new TMapPoint(centerP.getLatitude(), centerP.getLongitude());
        tmapdata.findAroundNamePOI(centerPoint, "문화시설", 3, 10,
                new TMapData.FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems != null) {
                            for (TMapPOIItem item : poiItems) {

                                TMapPoint point = item.getPOIPoint();

                                TMapMarkerItem marker = new TMapMarkerItem();
                                marker.setTMapPoint(point);
                                marker.setName(item.getPOIName());
                                marker.setVisible(TMapMarkerItem.VISIBLE);

                                tmap.addMarkerItem(item.getPOIName(), marker);
                                marker.setCalloutTitle("문화시설");
                                marker.setCalloutSubTitle(marker.getName());
                                marker.setCanShowCallout(true);
                                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.movie);
                                int newWidth = 70;
                                int newHeight = 70;
                                Bitmap scaledBitmap4 = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                                marker.setIcon(scaledBitmap4);                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "문화시설이 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}