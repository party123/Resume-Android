package com.bluemor.reddotface.activity;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.m;
import com.bluemor.reddotface.R;


/**

* �����ĵ�ͼͼ�㣬�������ɸ����ż�����ʾ�����ĵ�ͼ��Ϣ��������·���ֵ���ѧУ����԰�����ݡ�

* @author android_ls

*

*/

public class map extends Activity {

    /**��ͼ���������*/
    private BMapManager mBMapManager = null;

    /**��ʾ��ͼ��View*/

    private MapView mMapView = null;
    /**

     * ���о�����������KEYʱ��Ӧ������һ��Ҫд��my_app_Ӧ������Ҳ����˵"my_app_"�Ǳ���Ҫ�еģ���

     * �ٶȵ�ͼSDK�ṩ�ķ�������ѵģ��ӿ���ʹ�ô������ơ�������������Կ��key)���ſ�ʹ�ø���SDK��

     * */

    public static final String BAIDU_MAP_KEY = "07418AEC69BAAB7104C6230A6120C580DFFAEEBB";

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // ע�⣺���ڵ���setContentViewǰ��ʼ��BMapManager���󣬷���ᱨ��

        mBMapManager = new BMapManager(this.getApplicationContext());

        mBMapManager.init(BAIDU_MAP_KEY, new MKGeneralListener() {



            @Override

            public void onGetNetworkState(int iError) {

                if (iError == MKEvent.ERROR_NETWORK_CONNECT) {

                    Toast.makeText(map.this.getApplicationContext(),

                            "����������������^_^", 

                            Toast.LENGTH_LONG).show();

                }

            }



            @Override

            public void onGetPermissionState(int iError) {

                if (iError == MKEvent.ERROR_PERMISSION_DENIED) {

                    // ��ȨKey����

                    Toast.makeText(map.this.getApplicationContext(), 

                            "���� DemoApplication.java�ļ�������ȷ����ȨKey��", 

                            Toast.LENGTH_LONG).show();

                }

            }

        });



        setContentView(R.layout.map);

        mMapView = (MapView) this.findViewById(R.id.bmapView);

        // �����������õ����ſؼ�

        mMapView.setBuiltInZoomControls(true);

        // ��ȡ��ͼ��������������������ƽ�ƺ�����

        MapController mMapController = mMapView.getController();

        // �ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)

        // �����찲�ŵľ�γ�ȣ�39.915 * 1E6��116.404 * 1E6

       /* GeoPoint mGeoPoint = new GeoPoint(

                (int) (39.915 * 1E6), 

                (int) (116.404 * 1E6));*/



        // �Ϻ����ֶ�������GPSγ�Ⱦ���ֵ:31.224078,121.540419

        GeoPoint mGeoPoint = new GeoPoint(

                (int) (32.02895 * 1E6), 

                (int) (118.85052999999994 * 1E6));

        // ���õ�ͼ�����ĵ�

        mMapController.setCenter(mGeoPoint);

        // ���õ�ͼ�����ż��� ���ֵ��ȡֵ��Χ��[3,18]�� 

        mMapController.setZoom(13);

        // ��ǰ��ȫ����Χ����֧�ֶ������ʵʱ·����ѯ���һ�½����ͨ�������С�

        // �ڵ�ͼ����ʾʵʱ��ͨ��Ϣʾ

        // mMapView.setTraffic(true);

        // ���ǵ�ͼ�������������ʵ�ĵ�����ò���������ǵ�ͼ���������������Ϣ��������˽⵽����λ�ã����εȡ�

        mMapView.setTraffic(true);

        //mMapView.setSatellite(true);
    }

    // ��д���·���������API

    @Override

    protected void onResume() {

        mMapView.onResume();

        if (mBMapManager != null) {

            mBMapManager.start();

        }

        super.onResume();

    }

    @Override

    protected void onPause() {

        mMapView.onPause();

        if (mBMapManager != null) {

            mBMapManager.stop();

        }

        super.onPause();

    }

    @Override

    protected void onDestroy() {

        mMapView.destroy();

        if (mBMapManager != null) {

            mBMapManager.destroy();

            mBMapManager = null;

        }

        super.onDestroy();

    }

}
