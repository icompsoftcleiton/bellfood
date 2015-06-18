package br.edu.unisep.bellfoods.task;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;

/**
 * Created by Cleiton on 18/06/2015.
 */
public class QualificarPratoTask extends AsyncTask<Void, Void, Void> {

    private static final int CONNECTION_TIMEOUT = 1000 * 15;

    private static final String SERVER_ADDRESS = "http://www.icompsoft.com.br/bellfoods.php";

    private String metodo;

    private Integer prato;

    public QualificarPratoTask(String metodo, Integer prato) {
        this.metodo = metodo;
        this.prato = prato;
    }

    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpRequestParams,
                CONNECTION_TIMEOUT);
        return httpRequestParams;
    }

    @Override
    protected Void doInBackground(Void... params) {
        ArrayList<NameValuePair> dataToSend = new ArrayList<>();
        dataToSend.add(new BasicNameValuePair("action", this.metodo));
        dataToSend.add(new BasicNameValuePair("prato", this.prato.toString()));

        HttpParams httpRequestParams = getHttpRequestParams();

        HttpClient client = new DefaultHttpClient(httpRequestParams);
        HttpPost post = new HttpPost(SERVER_ADDRESS);

        try {
            post.setEntity(new UrlEncodedFormEntity(dataToSend));
            client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
