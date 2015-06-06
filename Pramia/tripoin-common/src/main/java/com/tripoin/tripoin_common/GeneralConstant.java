package com.tripoin.tripoin_common;

import android.os.Environment;

/**
 * Created by Achmad Fauzi on 6/4/2015 : 9:56 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public interface GeneralConstant {

    interface Punctuation {
        public final String SPACE = " ";
        public final String COLON = ":";
        public final String SEMI_COLON = ";";
        public final String COMMA = ",";
        public final String QUESTION = "?";
        public final String UNDERSCORE = "_";
        public final String HYPHEN = "-";
        public final String SLASH = "/";
        public final String STRING_EMPTY = "";
    }

    interface Rest{
        public String BASE_URL_DEV = "http://180.250.80.72:8080/mforce-rest/ws/service";
        public String BASE_URL_PROD = "http://180.250.80.72:8080/mforce-rest/ws/service";
        public String AUTHORIZATION = "Authorization";
        public String HTTP = "http";
        public String BASIC = "Basic";
        public String ACCEPT = "Accept";
        public String APPLICATION_JSON = "application/json";
        public String APPLICATION_XML = "application/xml";
        public int DEFAULT_HTTP_TIMEOUT = 30;
        public String HTTP_POST = "POST";
        public String HTTP_GET = "GET";
        public String WS_CONTEXT = "tripoin/wscontext/";
    }

    interface ApplicationMessage{
        public final String DEFAULT_ACTION_BAR_TITLE = "Tripoin Mobile";
        public final String WORKING_HOUR = "Working Hour";
        public final String NON_WORKING_HOUR = "Non Working Hour";
        public final String APP_MODE = "development";
        public final String DEVELOPMENT = "development";
        public final String PRODUCTION = "production";
    }

    interface ApplicationProperty{
        public final String TENANT_NAME = "Pramia";
        public final String APP_TARGET_DEFAULT_PATH = Environment.getExternalStorageDirectory().getPath()
                .concat(Punctuation.SLASH)
                .concat(TENANT_NAME);
        public final String APP_PROPERTY = APP_TARGET_DEFAULT_PATH.concat(".property");
        public final String SERVER_HOST_KEY = "SERVER_HOST";
        public final String SERVER_PORT_KEY ="SERVER_PORT";
    }


    interface Network{
        public String TYPE_WIFI = "1";
        public final String TYPE_MOBILE = "2";
        public final String TYPE_NOT_CONNECTED = "0";
        public final String WIFI_ENABLED = "Wifi enabled";
        public final String MOBILE_DATA_ENABLED = "Mobile data enabled";
        public final String NOT_CONNECTED_TO_INTERNET = "Not connected to Internet";
        public final String WIFI = "WIFI";
    }

    interface PhoneState{
        public final String PHONE_STATE_IDLE = "IDLE";
        public final String PHONE_STATE_OFF_HOOK = "OFF HOOK";
        public final String PHONE_STATE_RINGING = "RINGING";
    }

    interface BatteryState{
        public final String BATTERY_INTENT_HEALTH_EXTRA_KEY = "health";
        public final String BATTERY_DEAD = "Dead";
        public final String BATTERY_OVER_VOLTAGE = "Over Voltage";
        public final String BATTERY_OVER_HEAT = "Over Heat";
        public final String BATTERY_FAILURE = "Failure";
        public final String BATTERY_GOOD = "Good";
        public final String UNKNOWN = "Unknown";
    }

    interface BroadcastMessage{
        public final String RESTART_SERVICE = "restart_service";
    }

    interface Database{
        public final String DATABASE_NAME = "tripoin_mobile_".concat(ApplicationProperty.TENANT_NAME);
        public final int DB_VERSION = 1;
        public final String ID = "id";

        interface TableX{
            public final String name = "name";
            public final String address = "address";
        }
    }

    interface NativeModel{
        public String INTEGER = "integer";
        public String TEXT = "text";
        public String REAL = "real";
        public String BLOB = "blob";
    }
}
