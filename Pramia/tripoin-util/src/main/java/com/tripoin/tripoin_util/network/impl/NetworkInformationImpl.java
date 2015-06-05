package com.tripoin.tripoin_util.network.impl;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.tripoin.tripoin_common.GeneralConstant;
import com.tripoin.tripoin_util.GeneralConverter;
import com.tripoin.tripoin_util.GeneralValidation;
import com.tripoin.tripoin_util.network.INetworkInformation;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;


/**
 * Created by Achmad Fauzi on 5/29/2015 : 3:34 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class NetworkInformationImpl implements INetworkInformation {

    private TelephonyManager telephonyManager;
    private GsmCellLocation gsmCellLocation;
    private ConnectivityManager connectivityManager;

    public NetworkInformationImpl(TelephonyManager telephonyManager, ConnectivityManager connectivityManager) {
        this.telephonyManager = telephonyManager;
        this.connectivityManager = connectivityManager;
        gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
    }

    @Override
    public String getNetworkOperator() {
        return telephonyManager.getNetworkOperator();
    }

    @Override
    public int getGsmCellLocationLac() {
        return gsmCellLocation.getLac() & 0xffff;
    }

    @Override
    public int getGsmCellLocationId() {
        return gsmCellLocation.getCid() & 0xffff;
    }

    @Override
    public List<NeighboringCellInfo> getNeighboringCellInfos() {
        return telephonyManager.getNeighboringCellInfo();
    }

    @Override
    public String getDeviceId() {
        return telephonyManager.getDeviceId();
    }

    @Override
    public String getSubscriberId() {
        return telephonyManager.getSubscriberId();
    }

    @Override
    public String getSoftwareVersion() {
        return Build.VERSION.CODENAME.concat(GeneralConstant.Punctuation.UNDERSCORE).concat(Build.VERSION.RELEASE);
    }

    @Override
    public String getSIMCountryISO() {
        return telephonyManager.getSimCountryIso();
    }

    @Override
    public String getSIMOperatorName() {
        GeneralValidation generalValidation = new GeneralValidation();
        String operatorName;
        try{
            operatorName = telephonyManager.getNetworkOperatorName();
            if( generalValidation.isContainNumber(operatorName) ){
                operatorName = telephonyManager.getSimOperatorName();
            }
        }catch (Exception e){
            operatorName = telephonyManager.getNetworkOperatorName();
        }
        if( operatorName.length() <= 0 ){
            operatorName = GeneralConstant.Punctuation.HYPHEN;
        }
        return operatorName;
    }

    @Override
    public String getSIMSerial() {
        return telephonyManager.getSimSerialNumber();
    }

    @Override
    public String getNetworkCountryISO() {
        return telephonyManager.getNetworkCountryIso();
    }

    @Override
    public String getLineNumber() {
        return telephonyManager.getLine1Number();
    }

    @Override
    public String getMcc() {
        return getNetworkOperator().substring(0, 3);
    }

    @Override
    public String getMnc() {
        return getNetworkOperator().substring(3);
    }

    @Override
    public String getNetworkType() {
        int networkType = telephonyManager.getNetworkType();
        String result = "";
        switch (networkType)
        {
            case 7:
                result = "1xRTT";
                break;
            case 4:
                result = "CDMA";
                break;
            case 2:
                result = "EDGE";
                break;
            case 14:
                result = "eHRPD";
                break;
            case 5:
                result = "EVDO rev. 0";
                break;
            case 6:
                result = "EVDO rev. A";
                break;
            case 12:
                result = "EVDO rev. B";
                break;
            case 1:
                result = "GPRS";
                break;
            case 8:
                result = "HSDPA";
                break;
            case 10:
                result = "HSPA";
                break;
            case 15:
                result = "HSPA+";
                break;
            case 9:
                result = "HSUPA";
                break;
            case 11:
                result = "iDen";
                break;
            case 13:
                result = "LTE";
                break;
            case 3:
                result = "UMTS";
                break;
            case 0:
                result = "Unknown";
                break;
        }
        return result;
    }

    @Override
    public String getPhoneType() {
        String manufacturer = Build.MANUFACTURER;
        String brand = Build.BRAND;
        String model = Build.MODEL;
        GeneralConverter generalConverter = new GeneralConverter();
        if (model.startsWith(manufacturer)) {
            return generalConverter.capitalize(model);
        } else {
            return generalConverter.capitalize(manufacturer)
                    .concat(GeneralConstant.Punctuation.SPACE)
                    .concat(model)
                    .concat(GeneralConstant.Punctuation.SPACE)
                    .concat(brand);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public int[] getSignalStrengthGsm() {
        int[] result = new int[2];
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();
        try{
            if( cellInfos != null ){
                for (CellInfo info : telephonyManager.getAllCellInfo()) {
                    if (info instanceof CellInfoGsm) {
                        CellSignalStrengthGsm gsm = ((CellInfoGsm) info).getCellSignalStrength();
                        result[0] = gsm.getDbm();
                        result[1] = gsm.getAsuLevel();
                    } else if (info instanceof CellInfoCdma) {
                        CellSignalStrengthCdma cdma = ((CellInfoCdma) info).getCellSignalStrength();
                        result[0] = cdma.getDbm();
                        result[1] = cdma.getAsuLevel();
                    } else if (info instanceof CellInfoLte) {
                        CellSignalStrengthLte lte = ((CellInfoLte) info).getCellSignalStrength();
                        result[0] = lte.getDbm();
                        result[1] = lte.getAsuLevel();
                    } else if (info instanceof CellInfoWcdma){
                        CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) info).getCellSignalStrength();
                        result[0] = wcdma.getDbm();
                        result[1] = wcdma.getAsuLevel();
                    } else{
                        result[0] = -1;
                        result[1] = -1;
                    }
                }
            }else{
                result[0] = -1;
                result[1] = -1;
            }
        }catch (Exception e){
            Log.e("Error capture Signal Strength Mobile Data ", e.toString());
        }
        return result;
    }

    @Override
    public String getConnectivityStatus() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return GeneralConstant.Network.WIFI_ENABLED;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return GeneralConstant.Network.MOBILE_DATA_ENABLED;
        }
        return GeneralConstant.Network.NOT_CONNECTED_TO_INTERNET;
    }

    @Override
    public String getMobileIpAddress() {
        String ipAddressString = null;
        try {
            for ( Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for ( Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if ( !inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress()) ) {
                        ipAddressString = inetAddress.getHostAddress();
                    }
                }
            }
        } catch ( SocketException ex ) {
            Log.e( "MOBILE IP Exception", "Exception in Get IP Address " + ex.toString());
            ipAddressString = "-";
        }
        return ipAddressString;
    }
}
