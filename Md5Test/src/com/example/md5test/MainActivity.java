package com.example.md5test;


import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.security.MessageDigest;    
import java.security.NoSuchAlgorithmException;   
import java.util.List;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity {

	private TextView tv;
	private Button btnPost;
	private Vibrator vbFirst;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnPost = (Button)findViewById(R.id.button1);
		btnPost.setOnClickListener(clickLister);
		tv = (TextView)findViewById(R.id.textView1);
		try
		{
		tv.setText( md5("111111") );
		}
		catch(Exception e)
		{
		
		}
	}

	private Button.OnClickListener clickLister = new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			vbFirst = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
			//vbFirst.vibrate(new long[]{100,10,100,1000},-1);
			// vbFirst.vibrate(new long[] { 1, 1, 1, 1 }, 0);
			// 机关枪式按摩
			vbFirst.vibrate(new long[] { 1000, 100, 1000,100, 1000, 3000}, -1);
			// 抖动式按摩
			// vbFirst.vibrate(new long[] { 10,50,10}, 0);
			//mVibrator.vibrate(new long[] { 100, 50, 100, 50 }, 0);

		}
	};
	
	/**
	 * load AD
	 * 
	 * @author huyx
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addAD() {		
	FinalHttp fh = new FinalHttp();
	fh.get("http://www.816go/api/b.php?op=mobile&act=index_ad",
			new AjaxCallBack() {
				@Override
				public void onLoading(long count, long current) {

					super.onLoading(count, current);
				}

				@Override
				public void onSuccess(Object t) {
					super.onSuccess(t);
					String str = t.toString();

					JSONObject demoJson;
					try {
						demoJson = new JSONObject(str);
						JSONArray numberList = demoJson
								.getJSONArray("content");
						String s = numberList.toString();

						List<Entity_AD> list;
						Gson gson = new Gson();
						list = gson.fromJson(s,
								new TypeToken<List<Entity_AD>>() {
								}.getType());
						
						String userList = "";
						for (int i = 0; i < list.size(); i++) {							
							userList +=	list.get(i).getReturnValue();							
						}		
						btnPost.setText(userList);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onStart() {
					super.onStart();
				}

				@Override
				public void onFailure(Throwable t, int errorNo,
						String strMsg) {
					super.onFailure(t, errorNo, strMsg);
				}

			});

	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String Md5Method(String src) throws NoSuchAlgorithmException
	{
		 MessageDigest md5 = MessageDigest.getInstance("MD5");    
	        md5.update(src.getBytes());    
	        byte[] m = md5.digest();//加密      
	        return getString(m);   
	}
	
	 private static String getString(byte[] b){    
	        StringBuffer sb = new StringBuffer();    
	         for(int i = 0; i < b.length; i ++){    
	          sb.append(b[i]);    
	         }    
	         return sb.toString();   
	 }

	 //PHP和java MD5一致性问题解决
	 public String md5(String txt) {
         try{
              MessageDigest md = MessageDigest.getInstance("MD5");
              md.update(txt.getBytes("GBK"));    //问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
              StringBuffer buf=new StringBuffer();            
              for(byte b:md.digest()){
                   buf.append(String.format("%02x", b&0xff));        
              }
             return  buf.toString();
           }catch( Exception e ){
               e.printStackTrace();  

               return null;
            } 
    } 


}
